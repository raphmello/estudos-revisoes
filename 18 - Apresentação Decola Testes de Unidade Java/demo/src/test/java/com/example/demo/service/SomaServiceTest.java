package com.example.demo.service;

import com.example.demo.repository.SomaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class SomaServiceTest {

    @InjectMocks
    SomaService somaService;
    @Mock
    SomaRepository somaRepositoryMock;

    @Test
    void soma() {
        Integer soma = somaService.soma(1, 5);
        Assertions.assertEquals(6,soma);
        Mockito.verify(somaRepositoryMock,Mockito.times(2)).salvaNoBanco(Mockito.anyInt());
    }

}