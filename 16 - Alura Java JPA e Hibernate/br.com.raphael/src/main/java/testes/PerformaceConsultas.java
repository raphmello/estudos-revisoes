package testes;

import dao.CategoriaDao;
import dao.ClienteDao;
import dao.PedidoDao;
import dao.ProdutoDao;
import model.*;
import util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class PerformaceConsultas {
    public static void main(String[] args) {
        popularBancoDeDados();
        EntityManager em = JPAUtil.getEntityManager();
//        Pedido pedido = em.find(Pedido.class, 1L);
        PedidoDao pedidoDao = new PedidoDao(em);
        Pedido pedido = pedidoDao.buscarPedidoComCliente(1L);

        em.close();
        System.out.println(pedido.getCliente().getNome());
    }

    private static void popularBancoDeDados() {
        Categoria celulares = new Categoria("CELULARES","A");
        Categoria videogames = new Categoria("VIDEOGAMES","A");
        Categoria informatica = new Categoria("INFORMATICA","A");

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

        Produto produto = produtoDao.buscarPorId(1L);
        Produto produto2 = produtoDao.buscarPorId(2L);
        Produto produto3 = produtoDao.buscarPorId(3L);

        Pedido pedido = new Pedido(cliente);
        Pedido pedido2 = new Pedido(cliente);

        pedido.adicionarItem(new ItemPedido(10,pedido,produto));
        pedido.adicionarItem(new ItemPedido(40,pedido,produto2));

        pedido2.adicionarItem(new ItemPedido(2,pedido2,produto3));

        PedidoDao pedidoDao = new PedidoDao(em);

        pedidoDao.cadastrar(pedido);
        pedidoDao.cadastrar(pedido2);

        em.getTransaction().commit();
        em.close();
    }

}
