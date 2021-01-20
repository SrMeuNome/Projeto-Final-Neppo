package com.vinicius.neppo.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "categorias")
public class Categoria extends  AbstractEntity
{
    @Column(name = "nome", nullable = false)
    @Length(max = 100)
    private String nome;

    @Column(name = "descricao")
    @Length(max = 100)
    private String descricao;

    @Column(name = "link", nullable = false)
    private String link;

    @OneToMany(mappedBy = "categoria")
    private Collection<Secao> secoes;

    @OneToMany(mappedBy = "categoria")
    private Collection<Artigo> artigos;

    public Categoria() { super(); }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public String getLink()
    {
        return link;
    }

    public void setLink(String link)
    {
        this.link = link;
    }

    public Collection<Secao> getSecoes()
    {
        return secoes;
    }

    public void setSecoes(Collection<Secao> secoes)
    {
        this.secoes = secoes;
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
