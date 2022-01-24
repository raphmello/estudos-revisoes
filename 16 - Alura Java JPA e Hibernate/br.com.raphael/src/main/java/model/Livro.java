package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Livro extends Produto{

    private String autor;
    private Integer numerodePaginas;

    public Livro() {
    }

    public Livro(String autor, Integer numerodePaginas) {
        this.autor = autor;
        this.numerodePaginas = numerodePaginas;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getNumerodePaginas() {
        return numerodePaginas;
    }

    public void setNumerodePaginas(Integer numerodePaginas) {
        this.numerodePaginas = numerodePaginas;
    }
}
