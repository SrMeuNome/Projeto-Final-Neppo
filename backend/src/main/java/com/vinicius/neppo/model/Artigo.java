package com.vinicius.neppo.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "artigos")
public class Artigo extends  AbstractEntity
{
    @Column(name = "titulo", nullable = false)
    @Length(max = 100)
    private String titulo;

    @Column(name = "conteudo", nullable = false)
    @Length(max = 900)
    private String conteudo;

    @Column(name = "descricao", nullable = true)
    @Length(max = 100)
    private String descricao;

    @Column(name = "is_rascunho", nullable = false, columnDefinition = "TINYINT(1)")
    private boolean rascunho;

    @Column(name = "is_categoria", nullable = false, columnDefinition = "TINYINT(1)")
    private boolean artCategoria;

    @ManyToOne()
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @ManyToOne()
    @JoinColumn(name = "id_secao")
    private Secao secao;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "autor")
    private Usuario autor;

    @ManyToMany()
    @JoinTable(
        name = "artigo_tag",
        joinColumns = @JoinColumn(name = "id_artigo", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "id_tag", referencedColumnName = "id")
    )
    private Collection<Tag> tags;

    public Artigo() { super(); }

    public String getTitulo()
    {
        return titulo;
    }

    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }

    public String getConteudo()
    {
        return conteudo;
    }

    public void setConteudo(String conteudo)
    {
        this.conteudo = conteudo;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public boolean isRascunho()
    {
        return rascunho;
    }

    public void setRascunho(boolean rascunho)
    {
        this.rascunho = rascunho;
    }

    public boolean isArtCategoria()
    {
        return artCategoria;
    }

    public void setArtCategoria(boolean artCategoria)
    {
        this.artCategoria = artCategoria;
    }

    public Categoria getCategoria()
    {
        return categoria;
    }

    public void setCategoria(Categoria categoria)
    {
        this.categoria = categoria;
    }

    public Secao getSecao()
    {
        return secao;
    }

    public void setSecao(Secao secao)
    {
        this.secao = secao;
    }

    public Usuario getAutor()
    {
        return autor;
    }

    public void setAutor(Usuario autor)
    {
        this.autor = autor;
    }

    public Collection<Tag> getTags()
    {
        return tags;
    }

    public void setTags(Collection<Tag> tags)
    {
        this.tags = tags;
    }
}
