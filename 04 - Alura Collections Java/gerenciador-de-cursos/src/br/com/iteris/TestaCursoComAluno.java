package br.com.iteris;

import java.util.Iterator;
import java.util.Set;

public class TestaCursoComAluno {
    public static void main(String[] args) {
        Curso javaColecoes = new Curso("Dominando as coleções Java", "Raphael");

        javaColecoes.adiciona(new Aula("Trabalhando com ArrayList", 21));
        javaColecoes.adiciona(new Aula("Criando uma aula", 20));
        javaColecoes.adiciona(new Aula("Modelando coleções", 24));

        Aluno a1 = new Aluno("Rodrigo Turini",34675);
        Aluno a2 = new Aluno("Guilherme Silveira",5617);
        Aluno a3 = new Aluno("Mauricio Aniche",17645);

        javaColecoes.matricula(a1);
        javaColecoes.matricula(a2);
        javaColecoes.matricula(a3);
        System.out.println("Alunos matriculados: ");
//        javaColecoes.getAlunos().forEach(System.out::println);

        Set<Aluno> alunos = javaColecoes.getAlunos();
        Iterator<Aluno> iterator = alunos.iterator();
        while (iterator.hasNext()) {
            Aluno next = iterator.next();
            System.out.println(next);
        }

        System.out.println("O aluno " + a1 + " está matriculado?");
        System.out.println(javaColecoes.estaMatriculado(a1));

        System.out.println("Este Turini está matriculado?");
        Aluno turini = new Aluno("Rodrigo Turini", 34672);
        System.out.println(javaColecoes.estaMatriculado(turini));
    }
}
