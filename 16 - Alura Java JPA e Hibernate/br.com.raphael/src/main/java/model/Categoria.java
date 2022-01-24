package model;

import javax.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria {

    @EmbeddedId
    CategoriaId id;

    public Categoria() {
    }

    public Categoria(String name, String tipo) {
        id = new CategoriaId(name,tipo);
    }

    public CategoriaId getId() {
        return id;
    }

    public void setId(CategoriaId id) {
        this.id = id;
    }

    public String getName() {
        return this.id.getName();
    }
}
