package br.com.alura.bytebank.io.teste;

import br.com.alura.bytebank.io.LeitorCSV;
import br.com.alura.bytebank.model.Pagamento;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LeitorCSVTest {

    @Test
    void lerpagamentoDeAbril() throws IOException {
        LeitorCSV leitorCSV = new LeitorCSV();
        List<Pagamento> pagamentos = leitorCSV.ler(1);
        assertEquals(5, pagamentos.size());
    }
    @Test
    void lerpagamentoDeMaio() throws IOException {
        LeitorCSV leitorCSV = new LeitorCSV();
        List<Pagamento> pagamentos = leitorCSV.ler(3);
        assertEquals(5, pagamentos.size());
    }
    @Test
    void lerpagamentoDeJunho() throws IOException {
        LeitorCSV leitorCSV = new LeitorCSV();
        List<Pagamento> pagamentos = leitorCSV.ler(2);
        assertEquals(5, pagamentos.size());
    }
}