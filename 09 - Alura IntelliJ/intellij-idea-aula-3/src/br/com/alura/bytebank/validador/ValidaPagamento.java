package br.com.alura.bytebank.validador;

import br.com.alura.bytebank.model.Pagamento;
import br.com.alura.bytebank.model.Tipo;

import java.util.List;

public class ValidaPagamento {
    public ValidaPagamento() {
    }

    public void verificaTipo(Pagamento pagamento) {
        if (pagamento.getTipo().equals(Tipo.CREDITO)) {
            double valorComAcrescimo = pagamento.getValor() * 1.02;
            pagamento.setValor(valorComAcrescimo);
        }

        if (pagamento.getTipo().equals(Tipo.DEBITO)) {
            double valorComAcrescimo = pagamento.getValor() * 1.001;
            pagamento.setValor(valorComAcrescimo);
        }
    }

    public void verificaTipo(List<Pagamento> pagamentos) {
        for (Pagamento pagamento :
                pagamentos) {
            verificaTipo(pagamento);
        }
    }
}