package com.raphael.spring.data.service;

import com.raphael.spring.data.orm.Cargo;
import com.raphael.spring.data.orm.Funcionario;
import com.raphael.spring.data.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudFuncionarioService {

    private Boolean system = true;
    private final FuncionarioRepository repository;

    public CrudFuncionarioService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    public void inicial(Scanner scanner) {
        while (system) {
            System.out.println("Qual ação de funcionario deseja executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Salvar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Visualizar");
            System.out.println("4 - Deletar");

            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    salvar(scanner);
                    break;
                case 2:
                    atualizar(scanner);
                    break;
                case 3:
                    visualizar();
                    break;
                case 4:
                    deletar(scanner);
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }

    private void salvar(Scanner scanner) {
        System.out.println("Descrição do funcionario");
        Funcionario funcionario = new Funcionario();
        Cargo cargo = new Cargo();
        cargo.setId(1);
        funcionario.setCargo(cargo);
        repository.save(funcionario);
        System.out.println("Salvo");
    }

    private void atualizar(Scanner scanner) {
        System.out.println("Id");
        int id = scanner.nextInt();
        System.out.println("Nova descrição");
        Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        Cargo cargo = new Cargo();
        cargo.setId(1);
        funcionario.setCargo(cargo);
        repository.save(funcionario);
        System.out.println("Atualizado");
    }

    private void visualizar() {
        Iterable<Funcionario> funcionarios = repository.findAll();
        funcionarios.forEach(c -> {
            System.out.println(c);
        });
    }

    private void deletar(Scanner scanner) {
        System.out.println("Id");
        int id = scanner.nextInt();
        repository.deleteById(id);
        System.out.println("Funcionario " + id + " deletado.");
    }

}
