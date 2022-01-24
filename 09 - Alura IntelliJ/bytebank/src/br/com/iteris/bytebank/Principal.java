package br.com.iteris.bytebank;

import br.com.iteris.bytebank.model.Funcionario;

import java.time.LocalDate;

public class Principal {
    public static void main(String[] args) {
        System.out.println("Bem vindo ao ByteBank");
        Funcionario jose = new Funcionario("José",1,LocalDate.of(1990,2,10));
        Funcionario maria = new Funcionario("Maria",2,LocalDate.of(1990,2,10));
        System.out.println(jose);
        System.out.println(maria);
    }
}
