package entity;

public class ResumoDaSimplificacao {
    private String devedor;
    private String credor;
    private Double valor;

    public ResumoDaSimplificacao(String devedor, String credor, Double valor) {
        this.devedor = devedor;
        this.credor = credor;
        this.valor = valor;
    }

    public String getDevedor() {
        return devedor;
    }

    public String getCredor() {
        return credor;
    }

    public Double getValor() {
        return valor;
    }
}
