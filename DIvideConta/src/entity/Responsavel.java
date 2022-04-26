package entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Responsavel {
    private String nome;
    private Integer peso;
    private List<Conta> contas = new ArrayList<>();
    private Double valorPonderadoAPagarAntesDoDesconto = (double) 0;
    private Double valorPonderadoAPagarDepoisDoDesconto = (double) 0;

    public Responsavel(String nome, Integer peso, List<Conta> contas) {
        this.nome = nome;
        this.peso = peso;
        this.contas = contas;
    }

    public Responsavel(String nome, Integer peso) {
        this.nome = nome;
        this.peso = peso;
    }

    public String getNome() {
        return nome;
    }

    public List<Conta> getContas() {
        return Collections.unmodifiableList(contas);
    }

    public void adicionaConta(Conta conta) {
        contas.add(conta);
    }

    public Integer getPeso() {
        return peso;
    }

    public Double getValorPonderadoAPagarAntesDoDesconto() {
        return valorPonderadoAPagarAntesDoDesconto;
    }

    public void ponderarValores(Double valorTotal, Integer somaDePesos) {
        this.valorPonderadoAPagarAntesDoDesconto = valorTotal * (this.peso.doubleValue() / somaDePesos);
        double valorTotalDasContas = this.contas.stream().map(Conta::getValor).mapToDouble(BigDecimal::doubleValue).sum();
        this.valorPonderadoAPagarDepoisDoDesconto = this.valorPonderadoAPagarAntesDoDesconto - valorTotalDasContas;
    }

    public Double getValorPonderadoAPagarDepoisDoDesconto() {
        return valorPonderadoAPagarDepoisDoDesconto;
    }

    public void ajustarValorPonderadoAPagarDepoisDoDesconto(Double valor) {
        this.valorPonderadoAPagarDepoisDoDesconto += valor;
    }
}
