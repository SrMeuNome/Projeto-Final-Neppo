package com.vinicius.neppo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "secoes")
public class Secao extends AbstractEntity
{
    @Column(name = "nome", nullable = false)
    @Length(max = 100)
    private String nome;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    @OneToMany(mappedBy = "secao")
    private Collection<Artigo> artigos;

    public Secao() { super(); }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public Categoria getCategoria()
    {
        return categoria;
    }

    public void setCategoria(Categoria categoria)
    {
        this.categoria = categoria;
    }

    public Collection<Artigo> getArtigos()
    {
        return artigos;
    }

    public void setArtigos(Collection<Artigo> artigos)
    {
        this.artigos = artigos;
    }
}
