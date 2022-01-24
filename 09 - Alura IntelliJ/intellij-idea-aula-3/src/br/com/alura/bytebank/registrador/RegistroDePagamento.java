package br.com.alura.bytebank.registrador;

import br.com.alura.bytebank.model.Pagamento;
import br.com.alura.bytebank.validador.ValidaPagamento;

import java.util.ArrayList;
import java.util.List;

public class RegistroDePagamento {

    private final List<Pagamento> PAGAMENTOS = new ArrayList<>();
    private final ValidaPagamento validaPagamento = new ValidaPagamento();

    public void registra(List<Pagamento> pagamentos) {
        for (Pagamento pagamento : pagamentos) {
            salva(pagamento);
        }
    }

    private void salva(Pagamento pagamento) {
        PAGAMENTOS.add(pagamento);
        System.out.println("Pagamento realizado " + pagamento);
    }

    public void exibeEfetuados() {
        if (PAGAMENTOS.isEmpty()) {
            System.out.println("Não tem pagamentos registrados");
        } else {
            System.out.println("Todos os pagamentos");
            for (Pagamento pagamentosRegistrado : PAGAMENTOS) {
                System.out.println(pagamentosRegistrado);
            }
        }
    }

}