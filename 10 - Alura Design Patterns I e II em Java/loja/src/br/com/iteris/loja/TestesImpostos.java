package br.com.iteris.loja;

import br.com.iteris.loja.imposto.CalculadoraDeImpostos;
import br.com.iteris.loja.imposto.ICMS;
import br.com.iteris.loja.imposto.ISS;
import br.com.iteris.loja.orcamento.Orcamento;

import java.math.BigDecimal;

public class TestesImpostos {
    public static void main(String[] args) {
        Orcamento orcamento = new Orcamento();
        CalculadoraDeImpostos calculadora = new CalculadoraDeImpostos();
        System.out.println(calculadora.calcular(orcamento, new ISS(new ICMS(null))));
    }
}
