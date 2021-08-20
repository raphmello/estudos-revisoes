package br.com.iteris.loja.desconto;

import br.com.iteris.loja.orcamento.Orcamento;

import java.math.BigDecimal;

public class CalculadoraDeDescontos {
    public BigDecimal calcular(Orcamento orcamento) {
        Desconto desconto = new DescontoParaMaisDeCincoItens(new DescontoParaValorMaiorQue500(new SemDesconto()));

        return desconto.calcular(orcamento);
    }
}
