package service;

import entity.Conta;
import entity.ContasConsolidadas;
import entity.Responsavel;
import entity.ResumoDaSimplificacao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ContasConsolidadasService {

    public void gerarDivisao(ContasConsolidadas contasConsolidadas) {
        List<BigDecimal> valores = new ArrayList<>();
        List<Integer> pesos = new ArrayList<>();
        contasConsolidadas.getResponsaveis().forEach(r -> {
            Integer peso = r.getPeso();
            pesos.add(peso);
            for (Conta conta : r.getContas()) {
                valores.add(conta.getValor());
            }
        });

        double somaDosValores = valores.stream()
                .map(BigDecimal::doubleValue).mapToDouble(Double::doubleValue).sum();
        int somaDePesos = pesos.stream().mapToInt(Integer::intValue).sum();

        contasConsolidadas
                .getResponsaveis()
                .forEach(r -> r.ponderarValores(somaDosValores,somaDePesos));

    }

    public void simplificarDivisoes(ContasConsolidadas contasConsolidadas) {
        List<Responsavel> participantesOrdenados = getResponsaveisOrdenadosPorDevedoresAsc(contasConsolidadas.getResponsaveis());
        int qtdParticipantes = participantesOrdenados.size();
        for (int i = 0; i < qtdParticipantes; i++) {
            Responsavel maiorDevedor = participantesOrdenados.get(qtdParticipantes - 1 - i);
            Double dividaDoMaiorDevedor = maiorDevedor.getValorPonderadoAPagarDepoisDoDesconto();
            if (dividaDoMaiorDevedor > 0) {
                for (int ii = 0; ii < qtdParticipantes && dividaDoMaiorDevedor > 0; ii++) {
                    Responsavel menorDevedor = participantesOrdenados.get(ii);

                    if (menorDevedor.getNome().equalsIgnoreCase(maiorDevedor.getNome()))
                        continue;

                    Double dividaDoMenorDevedor = menorDevedor.getValorPonderadoAPagarDepoisDoDesconto();
                    Double valorParaPagamento = 0.;
                    if (dividaDoMenorDevedor < 0 && dividaDoMaiorDevedor >= Math.abs(dividaDoMenorDevedor)) {
                            valorParaPagamento = dividaDoMenorDevedor;
                            dividaDoMaiorDevedor += valorParaPagamento;
                            maiorDevedor.ajustarValorPonderadoAPagarDepoisDoDesconto(valorParaPagamento);
                            menorDevedor.ajustarValorPonderadoAPagarDepoisDoDesconto(-dividaDoMenorDevedor);
                    } else if (dividaDoMenorDevedor < 0 && dividaDoMaiorDevedor < Math.abs(dividaDoMenorDevedor)) {
                        valorParaPagamento = dividaDoMaiorDevedor;
                        dividaDoMaiorDevedor -= valorParaPagamento;
                        maiorDevedor.ajustarValorPonderadoAPagarDepoisDoDesconto(-valorParaPagamento);
                        menorDevedor.ajustarValorPonderadoAPagarDepoisDoDesconto(valorParaPagamento);
                    }
                    contasConsolidadas.
                            addResumoDaSimplificacao(
                                    new ResumoDaSimplificacao(maiorDevedor.getNome(),
                                    menorDevedor.getNome(),
                                    Math.abs(valorParaPagamento)));
                }
            }
        }
    }

    public List<Responsavel> getResponsaveisOrdenadosPorDevedoresAsc(Set<Responsavel> responsaveis) {
        return responsaveis.stream().sorted(Comparator.comparingDouble(Responsavel::getValorPonderadoAPagarDepoisDoDesconto)).collect(Collectors.toList());
    }

}
