package model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data = LocalDate.now();
    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;
    @Column(name = "valor_total")
    private BigDecimal valorTotal = BigDecimal.ZERO;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    List<ItemPedido> itens = new ArrayList<>();

    public Pedido() {
    }

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }

    public void adicionarItem(ItemPedido itemPedido) {
        itemPedido.setPedido(this);
        itens.add(itemPedido);
        valorTotal = valorTotal.add(itemPedido.getValor());
    }

    public Long getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<ItemPedido> getItens() {
        return Collections.unmodifiableList(itens);
    }
}

