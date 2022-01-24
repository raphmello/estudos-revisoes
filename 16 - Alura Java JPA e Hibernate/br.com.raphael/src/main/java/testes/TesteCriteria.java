package testes;

import dao.CategoriaDao;
import dao.ClienteDao;
import dao.PedidoDao;
import dao.ProdutoDao;
import model.*;
import util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class TesteCriteria {
    public static void main(String[] args) {
        popularBancoDeDados();
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        List<Produto> produtos = produtoDao.buscarPorParametrosComCriteria("", null, LocalDate.now());
        produtos.forEach(p -> System.out.println(p.getNome()));

    }

    private static void popularBancoDeDados() {
        Categoria celulares = new Categoria("CELULARES","A");
        Categoria videogames = new Categoria("VIDEOGAMES","A");
        Categoria informatica = new Categoria("INFORMATICA","A");

        Produto celular = new Produto("Xiaomi","Muito legal",new BigDecimal("800"), celulares);
        Produto videogame = new Produto("PS5","Playstation 5",new BigDecimal("800"), celulares);
        Produto macbook = new Produto("Macbook","Macbook Pro",new BigDecimal("800"), celulares);

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        categoriaDao.cadastrar(videogames);
        categoriaDao.cadastrar(informatica);
        produtoDao.cadastrar(celular);
        produtoDao.cadastrar(videogame);
        produtoDao.cadastrar(macbook);

        em.getTransaction().commit();
        em.close();
    }

}
