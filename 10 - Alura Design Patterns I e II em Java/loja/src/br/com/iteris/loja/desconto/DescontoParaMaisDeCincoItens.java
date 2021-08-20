package br.com.iteris.loja.desconto;

import br.com.iteris.loja.orcamento.Orcamento;

import java.math.BigDecimal;

public class DescontoParaMaisDeCincoItens extends Desconto {

    public DescontoParaMaisDeCincoItens(Desconto proximo) {
        super(proximo);
    }

    public BigDecimal efetuarCalculo(Orcamento orcamento) {
        return orcamento.getValor().multiply(new BigDecimal("0.1"));
    }

    @Override
    public boolean deveAplicar(Orcamento orcamento) {
        return orcamento.getQuantidadeItens() > 5;
    }

}
