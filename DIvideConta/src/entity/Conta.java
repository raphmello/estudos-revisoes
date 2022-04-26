package entity;

import java.math.BigDecimal;

public class Conta {
    private BigDecimal valor;

    public Conta(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
