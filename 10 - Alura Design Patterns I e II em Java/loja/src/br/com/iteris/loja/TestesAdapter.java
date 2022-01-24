package br.com.iteris.loja;

import br.com.iteris.loja.http.JavaHttpClient;
import br.com.iteris.loja.orcamento.Orcamento;
import br.com.iteris.loja.orcamento.RegistroDeOrcamento;

import java.math.BigDecimal;

public class TestesAdapter {
    public static void main(String[] args) {
        Orcamento orcamento = new Orcamento();
        orcamento.aprovar();
        orcamento.finalizar();

        RegistroDeOrcamento registro = new RegistroDeOrcamento(new JavaHttpClient());
        registro.registrar(orcamento);
    }
}
