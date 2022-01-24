package com.example.demo.services;

import com.example.demo.repository.LogicaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class LogicaServiceTest {

    @InjectMocks
    LogicaService logicaService;
    @Mock
    LogicaRepository logicaRepositoryMock;

    @Test
    void soma() {
        Integer soma = logicaService.soma(1, 10);
        assertEquals(11,soma);
        verify(logicaRepositoryMock, times(2)).salvaNoBancoDeDados(anyInt());
    }
}