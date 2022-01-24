package br.com.iteris.loja.desconto;

import br.com.iteris.loja.orcamento.Orcamento;

import java.math.BigDecimal;

public class DescontoParaValorMaiorQue500 extends Desconto {

    public DescontoParaValorMaiorQue500(Desconto proximo) {
        super(proximo);
    }

    public BigDecimal efetuarCalculo(Orcamento orcamento) {
        return orcamento.getValor().multiply(new BigDecimal("0.05"));
    }

    @Override
    public boolean deveAplicar(Orcamento orcamento) {
        return orcamento.getValor().compareTo(new BigDecimal("500")) > 0;
    }

}
