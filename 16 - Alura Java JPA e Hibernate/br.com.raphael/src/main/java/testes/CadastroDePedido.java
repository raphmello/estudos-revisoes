package testes;

import dao.CategoriaDao;
import dao.ClienteDao;
import dao.PedidoDao;
import dao.ProdutoDao;
import model.*;
import util.JPAUtil;
import vo.RelatorioDeVendasVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDePedido {
    public static void main(String[] args) {
        propularBancoDeDados();


        EntityManager em = JPAUtil.getEntityManager();

        em.getTransaction().begin();

        ProdutoDao produtoDao = new ProdutoDao(em);
        Produto produto = produtoDao.buscarPorId(1L);
        Produto produto2 = produtoDao.buscarPorId(2L);
        Produto produto3 = produtoDao.buscarPorId(3L);

        ClienteDao clienteDao = new ClienteDao(em);
        Cliente cliente = clienteDao.buscarPorId(1L);

        Pedido pedido = new Pedido(cliente);
        Pedido pedido2 = new Pedido(cliente);

        pedido.adicionarItem(new ItemPedido(10,pedido,produto));
        pedido.adicionarItem(new ItemPedido(40,pedido,produto2));

        pedido2.adicionarItem(new ItemPedido(2,pedido2,produto3));

        PedidoDao pedidoDao = new PedidoDao(em);

        pedidoDao.cadastrar(pedido);
        pedidoDao.cadastrar(pedido2);

        em.getTransaction().commit();

        BigDecimal totalVendido = pedidoDao.valorTotalVendido();
        System.out.println("VALOR TOTAL: " + totalVendido);

        List<RelatorioDeVendasVo> relatorioDeVendasVos = pedidoDao.relatorioDeVendas();
        for (RelatorioDeVendasVo vendas :
                relatorioDeVendasVos) {
            System.out.println(vendas);
        }
    }

    private static void propularBancoDeDados() {
        Categoria celulares = new Categoria("CELULARES");
        Categoria videogames = new Categoria("VIDEOGAMES");
        Categoria informatica = new Categoria("INFORMATICA");

        Produto celular = new Produto("Xiaomi","Muito legal",new BigDecimal("800"), celulares);
        Produto videogame = new Produto("PS5","Playstation 5",new BigDecimal("800"), celulares);
        Produto macbook = new Produto("Macbook","Macbook Pro",new BigDecimal("800"), celulares);

        Cliente cliente = new Cliente("Raphael","123456");

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ClienteDao clienteDao = new ClienteDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        categoriaDao.cadastrar(videogames);
        categoriaDao.cadastrar(informatica);
        produtoDao.cadastrar(celular);
        produtoDao.cadastrar(videogame);
        produtoDao.cadastrar(macbook);
        clienteDao.cadastrar(cliente);

        em.getTransaction().commit();
        em.close();
    }

}
