package br.com.iteris;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {

    @Test
    public void deveriaSomarDoisNumerosPositivos() {
        Calculadora cal = new Calculadora();
        int soma = cal.somar(3,7);
        Assertions.assertEquals(10,soma);
    }

}
