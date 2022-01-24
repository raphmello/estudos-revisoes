package com.raphael.spring.data.service;

import com.raphael.spring.data.orm.UnidadeTrabalho;
import com.raphael.spring.data.repository.UnidadeTrabalhoRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudUnidadeTrabalhoService {

    private Boolean system = true;
    private final UnidadeTrabalhoRepository repository;

    public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository repository) {
        this.repository = repository;
    }

    public void inicial(Scanner scanner) {
        while (system) {
            System.out.println("Qual ação de unidadeTrabalho deseja executar?");
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
        System.out.println("Descrição do unidadeTrabalho");
        String descricao = scanner.next();
        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
        unidadeTrabalho.setDescricao(descricao);
        repository.save(unidadeTrabalho);
        System.out.println("Salvo");
    }

    private void atualizar(Scanner scanner) {
        System.out.println("Id");
        int id = scanner.nextInt();
        System.out.println("Nova descrição");
        String descricao = scanner.next();
        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
        unidadeTrabalho.setId(id);
        unidadeTrabalho.setDescricao(descricao);
        repository.save(unidadeTrabalho);
        System.out.println("Atualizado");
    }

    private void visualizar() {
        Iterable<UnidadeTrabalho> unidadeTrabalhos = repository.findAll();
        unidadeTrabalhos.forEach(c -> {
            System.out.println(c);
        });
    }

    private void deletar(Scanner scanner) {
        System.out.println("Id");
        int id = scanner.nextInt();
        repository.deleteById(id);
        System.out.println("UnidadeTrabalho " + id + " deletado.");
    }

}
