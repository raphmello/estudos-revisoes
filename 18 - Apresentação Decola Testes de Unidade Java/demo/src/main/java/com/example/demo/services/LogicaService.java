package com.example.demo.services;

import com.example.demo.repository.LogicaRepository;

public class LogicaService {

    private LogicaRepository repository;

    public LogicaService(LogicaRepository repository) {
        this.repository = repository;
    }

    public Integer soma(Integer a, Integer b) {
        repository.salvaNoBancoDeDados(a);
        repository.salvaNoBancoDeDados(b);
        return a + b;
    }
}
