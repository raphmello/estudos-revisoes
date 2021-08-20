package br.com.iteris.loja.orcamento;

import br.com.iteris.loja.DomainException;
import br.com.iteris.loja.http.HttpAdapter;
import br.com.iteris.loja.orcamento.situacao.Aprovado;

import java.util.Map;

public class RegistroDeOrcamento {

    private HttpAdapter http;

    public RegistroDeOrcamento(HttpAdapter http) {
        this.http = http;
    }

    public void registrar (Orcamento orcamento) {
        if(!orcamento.isFinalizado()) {
            throw new DomainException("Orçamento não finalizado!");
        }
        String url = "http://api.externa/orcamento";
        Map<String, Object> dados = Map.of(
                "valor", orcamento.getValor(),
                "quantidadeItens", orcamento.getQuantidadeItens()
        );
        http.post(url, dados);
    }
}
