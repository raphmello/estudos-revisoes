package dao;

import model.Produto;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ProdutoDao {

    private EntityManager em;

    public ProdutoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Produto produto) {
        this.em.persist(produto);
    }

    public Produto buscarPorId(Long id) {
        return this.em.find(Produto.class, id);
    }

    public List<Produto> buscarTodos() {
        return this.em.createQuery("SELECT p FROM Produto p",Produto.class).getResultList();
    }

    public List<Produto> buscarPorNome(String nome) {
        String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
        return this.em.createQuery(jpql,Produto.class)
                .setParameter("nome", nome)
                .getResultList();
    }

    public List<Produto> buscarPorNomeDaCategoria(String nome) {
        return this.em.createNamedQuery("Produto.produtosPorCategoria",Produto.class)
                .setParameter("nome", nome)
                .getResultList();
    }

    public BigDecimal buscarPrecoDoProdutoComNome(String nome) {
        String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :nome";
        return this.em.createQuery(jpql,BigDecimal.class)
                .setParameter("nome", nome)
                .getSingleResult();
    }

    public List<Produto> buscarPorParametrosComCriteria(String nome, BigDecimal preco, LocalDate dataCadastro) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
        Root<Produto> from = query.from(Produto.class);

        Predicate filtros = builder.and();
        if (nome != null && !nome.trim().isEmpty()) {
            filtros = builder.and(filtros, builder.equal(from.get("nome"),nome));
        }
        if (preco != null) {
            filtros = builder.and(filtros, builder.equal(from.get("preco"),preco));
        }
        if (dataCadastro != null) {
            filtros = builder.and(filtros, builder.equal(from.get("dataCadastro"),dataCadastro));
        }
        query.where(filtros);
        return em.createQuery(query).getResultList();
    }

}
