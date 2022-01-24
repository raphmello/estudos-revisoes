package br.com.iteris.loja.pedido.acao;

import br.com.iteris.loja.pedido.Pedido;

public class LogDePedido implements AcaoAposGerarPedido{
    @Override
    public void executarAcao(Pedido pedido) {
        System.out.println("Pedido foi gerado: " + pedido);
    }
}
