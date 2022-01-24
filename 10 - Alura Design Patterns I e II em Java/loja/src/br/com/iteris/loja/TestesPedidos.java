package br.com.iteris.loja;

import br.com.iteris.loja.pedido.GeraPedido;
import br.com.iteris.loja.pedido.GeraPedidoHandler;
import br.com.iteris.loja.pedido.acao.EnviarEmailPedido;
import br.com.iteris.loja.pedido.acao.LogDePedido;
import br.com.iteris.loja.pedido.acao.SalvarPedidoNoBancoDeDados;

import java.math.BigDecimal;
import java.util.Arrays;

public class TestesPedidos {
    public static void main(String[] args) {
        String cliente = "Rodrigo";
        BigDecimal valorOrcamento = new BigDecimal("300");
        int quantidadeItens = Integer.parseInt("2");

        GeraPedido gerador = new GeraPedido(cliente, valorOrcamento, quantidadeItens);
        GeraPedidoHandler handler = new GeraPedidoHandler(Arrays.asList(
                new SalvarPedidoNoBancoDeDados(),
                new EnviarEmailPedido(),
                new LogDePedido()
        ));
        handler.execute(gerador);
    }
}
