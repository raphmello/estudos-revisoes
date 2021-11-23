package com.example.demo.service;

import com.example.demo.repository.SomaRepository;

public class SomaService {

    SomaRepository repository;

    public SomaService(SomaRepository repository) {
        this.repository = repository;
    }

    public Integer soma(Integer a, Integer b) {
        repository.salvaNoBanco(a);
        repository.salvaNoBanco(b);
        return a + b;
    }
}
