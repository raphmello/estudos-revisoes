package br.com.alura.rh.model;

import java.math.BigDecimal;

public class Terceirizado  {

    private String empresa;
    private DadosPessoais dadosPessoais;

    public Terceirizado(String empresa, DadosPessoais dadosPessoais) {
        this.empresa = empresa;
        this.dadosPessoais = dadosPessoais;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
}
