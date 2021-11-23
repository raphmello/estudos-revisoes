package com.example.demo.services;

import com.example.demo.repository.LogicaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class LogicaServiceTest {

    @InjectMocks
    LogicaService logicaService;
    @Mock
    LogicaRepository logicaRepositoryMock;

    @Test
    void soma() {
        Integer soma = logicaService.soma(1, 10);
        Assertions.assertEquals(11,soma);
        Mockito.verify(logicaRepositoryMock, Mockito.times(2)).salvaNoBancoDeDados(Mockito.anyInt());
    }
}