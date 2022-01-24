package br.com.iteris.loja.imposto;

import br.com.iteris.loja.orcamento.Orcamento;

import java.math.BigDecimal;

public abstract class Imposto {

    private Imposto outro;

    public Imposto(Imposto outro) {
        this.outro = outro;
    }

    protected abstract BigDecimal realizarCalculo(Orcamento orcamento);

    public BigDecimal calcular(Orcamento orcamento) {
        BigDecimal valorImposto = realizarCalculo(orcamento);
        BigDecimal valorDoOutroImposto = BigDecimal.ZERO;
        if(outro != null) {
            valorDoOutroImposto = outro.calcular(orcamento);
        }
        return valorImposto.add(valorDoOutroImposto);
    }
}
