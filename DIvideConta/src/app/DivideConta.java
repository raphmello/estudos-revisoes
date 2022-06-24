package app;

import entity.Conta;
import entity.ContasConsolidadas;
import entity.Responsavel;
import service.ContasConsolidadasService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class DivideConta {

    public static final String TXT_PATH_CASA = "C:\\workspace\\estudos-revisoes\\piracaia-casa.txt";
    public static final String TXT_PATH_COMPRAS_GERAIS = "C:\\workspace\\estudos-revisoes\\piracaia-compras-gerais.txt";
    public static final String TXT_PATH_CARNES = "C:\\workspace\\estudos-revisoes\\piracaia-carnes.txt";
    public static final String TXT_PATH_CERVEJAS = "C:\\workspace\\estudos-revisoes\\piracaia-cervejas.txt";
    public static final String COMMA_DELIMITER = ",";
    public static final String TEM_QUE_PAGAR = " tem que pagar ";
    public static final String TEM_QUE_RECEBER = " tem que receber ";
    public static final String QUITADO = " quitado.";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void main(String[] args) {
        ContasConsolidadas consolidacaoCasa = new ContasConsolidadas();
        ContasConsolidadas consolidacaoComprasGerais = new ContasConsolidadas();
        ContasConsolidadas consolidacaoCarnes = new ContasConsolidadas();
        ContasConsolidadas consolidacaoCervejas = new ContasConsolidadas();

        ContasConsolidadasService divisaoCasa = calcularDivisoes(consolidacaoCasa, TXT_PATH_CASA, "CALCULO CASA");
        ContasConsolidadasService divisaoComprasGerais = calcularDivisoes(consolidacaoComprasGerais, TXT_PATH_COMPRAS_GERAIS, "CALCULO COMPRAS GERAIS");
        ContasConsolidadasService divisaoCarnes = calcularDivisoes(consolidacaoCarnes, TXT_PATH_CARNES, "CALCULO CARNES");
        ContasConsolidadasService divisaoCervejas = calcularDivisoes(consolidacaoCervejas, TXT_PATH_CERVEJAS, "CALCULO BEBIDAS ALCOOLICAS");

        consolidacaoCasa.getResponsaveis().forEach(respGeral -> {
            if(!Objects.isNull(consolidacaoComprasGerais.buscarResponsavel(respGeral.getNome()))) {
                Double valor = consolidacaoComprasGerais.buscarResponsavel(respGeral.getNome()).getValorPonderadoAPagarDepoisDoDesconto();
                respGeral.ajustarValorPonderadoAPagarDepoisDoDesconto(valor);
            }
           if(!Objects.isNull(consolidacaoCarnes.buscarResponsavel(respGeral.getNome()))) {
               Double valor = consolidacaoCarnes.buscarResponsavel(respGeral.getNome()).getValorPonderadoAPagarDepoisDoDesconto();
               respGeral.ajustarValorPonderadoAPagarDepoisDoDesconto(valor);
           }
            if(!Objects.isNull(consolidacaoCervejas.buscarResponsavel(respGeral.getNome()))) {
                Double valor = consolidacaoCervejas.buscarResponsavel(respGeral.getNome()).getValorPonderadoAPagarDepoisDoDesconto();
                respGeral.ajustarValorPonderadoAPagarDepoisDoDesconto(valor);
            }
        });

        divisaoCasa.simplificarDivisoes(consolidacaoCasa);
        System.out.println("\n=============QUEM PAGA QUEM=================");
        consolidacaoCasa.getResumoDaSimplificacaoList().forEach(list -> {
            if (list.getValor() > 0) {
                String output = String.format("%s deve para %s %.2f reais.", list.getDevedor().toUpperCase(), list.getCredor().toUpperCase(), list.getValor());
                System.out.println(ANSI_CYAN + output + ANSI_RESET);
            }
        });
    }

    private static ContasConsolidadasService calcularDivisoes(ContasConsolidadas consolidacao, String filePath, String title) {
        List<List<String>> records = lerArquivoTxt(filePath);


        records.forEach(rec -> {
            Responsavel responsavel = consolidacao.buscarResponsavel(rec.get(0));
            if (Objects.isNull(responsavel)) {
                responsavel = new Responsavel(rec.get(0), Integer.parseInt(rec.get(1)), new ArrayList<>());
                consolidacao.adicionaParticipante(responsavel);
            }
            for (int i = 2;i < rec.size();i++) {
                responsavel.adicionaConta(new Conta(BigDecimal.valueOf(Double.parseDouble(rec.get(i)))));
            }
        });
        System.out.println("\n=============" + title + "=================");
        ContasConsolidadasService service = new ContasConsolidadasService();
        service.gerarDivisao(consolidacao);
        List<Responsavel> responsaveisOrdenadosPorDevedoresAsc = service.getResponsaveisOrdenadosPorDevedoresAsc(consolidacao.getResponsaveis());
        responsaveisOrdenadosPorDevedoresAsc
                .forEach(responsavel -> {
                    Double valorPagarReceber = responsavel.getValorPonderadoAPagarDepoisDoDesconto();
                    String pagarDevolver = valorPagarReceber > 0 ? TEM_QUE_PAGAR : TEM_QUE_RECEBER;
                    String color = pagarDevolver.equals(TEM_QUE_PAGAR) ? ANSI_RED : ANSI_GREEN;
                    pagarDevolver = valorPagarReceber == 0 ? QUITADO : pagarDevolver + String.format("%.2f",Math.abs(valorPagarReceber));
                    System.out.println(color + responsavel.getNome().toUpperCase() + pagarDevolver + ANSI_RESET);
                });
        return service;
    }

    private static List<List<String>> lerArquivoTxt(String txtPath) {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(txtPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(line.startsWith("//")) {
                    continue;
                }
                String[] values = line.split(COMMA_DELIMITER);
                records.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return records;
    }
}
