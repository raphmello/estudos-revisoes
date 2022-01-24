package br.com.iteris;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class TestaPerformance {

    public static void main(String[] args) {

        Collection<Integer> numeros = new HashSet<>();

        long inicio = System.currentTimeMillis();

        long inicioFor = System.currentTimeMillis();

        for (int i = 1; i <= 100000; i++) {
            numeros.add(i);
        }
        long fimFor = System.currentTimeMillis();
        long tempoDeExecucao = fimFor - inicioFor;
        System.out.println("Tempo gasto no for: " + tempoDeExecucao);

        for (Integer numero : numeros) {
            numeros.contains(numero);
        }

        long fim = System.currentTimeMillis();

        tempoDeExecucao = fim - inicio;

        System.out.println("Tempo gasto: " + tempoDeExecucao);

    }

}
