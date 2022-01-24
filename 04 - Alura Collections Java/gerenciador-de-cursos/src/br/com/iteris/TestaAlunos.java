package br.com.iteris;

import java.util.HashSet;
import java.util.Set;

public class TestaAlunos {
    public static void main(String[] args) {
        Set<String> alunos = new HashSet<>();
        alunos.add("Raphael Mello");
        alunos.add("Alberto Souza");
        alunos.add("Nico");
        alunos.add("Sergio Lopes");
        alunos.add("Renan Saggio");
        alunos.add("Mauricio");
        alunos.add("Alberto Souza");

        boolean raphaelEstaMatriculado = alunos.contains("Raphael Mello");
        alunos.remove("Nico");
        System.out.println(raphaelEstaMatriculado);

        alunos.forEach(System.out::println);

        System.out.println(alunos);
    }
}
