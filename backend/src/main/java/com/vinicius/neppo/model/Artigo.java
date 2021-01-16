package com.vinicius.neppo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "artigos")
public class Artigo
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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
    private boolean is_rascunho;

    @Column(name = "is_categoria", nullable = false, columnDefinition = "TINYINT(1)")
    private boolean is_categoria;

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

    @JsonIgnore
    @ManyToMany()
    @JoinTable(
        name = "artigo_tag",
        joinColumns = @JoinColumn(name = "id_artigo", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "id_tag", referencedColumnName = "id")
    )
    private List<Tag> tags;

    public Artigo() {}

    public Artigo(long id, String titulo, String conteudo, String descricao, boolean is_rascunho, boolean is_categoria, Categoria categoria, Secao secao, Usuario autor, List<Tag> tags)
    {
        this.id = id;
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.descricao = descricao;
        this.is_rascunho = is_rascunho;
        this.is_categoria = is_categoria;
        this.categoria = categoria;
        this.secao = secao;
        this.autor = autor;
        this.tags = tags;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

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

    public boolean isIs_rascunho()
    {
        return is_rascunho;
    }

    public void setIs_rascunho(boolean is_rascunho)
    {
        this.is_rascunho = is_rascunho;
    }

    public boolean isIs_categoria()
    {
        return is_categoria;
    }

    public void setIs_categoria(boolean is_categoria)
    {
        this.is_categoria = is_categoria;
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

    public List<Tag> getTags()
    {
        return tags;
    }

    public void setTags(List<Tag> tags)
    {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artigo artigo = (Artigo) o;
        return id == artigo.id &&
                is_rascunho == artigo.is_rascunho &&
                is_categoria == artigo.is_categoria &&
                Objects.equals(titulo, artigo.titulo) &&
                Objects.equals(conteudo, artigo.conteudo) &&
                Objects.equals(descricao, artigo.descricao) &&
                Objects.equals(categoria, artigo.categoria) &&
                Objects.equals(secao, artigo.secao) &&
                Objects.equals(autor, artigo.autor) &&
                Objects.equals(tags, artigo.tags);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, titulo, conteudo, descricao, is_rascunho, is_categoria, categoria, secao, autor, tags);
    }

    @Override
    public String toString()
    {
        return "Artigo{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", conteudo='" + conteudo + '\'' +
                ", desc='" + descricao + '\'' +
                ", is_rascunho=" + is_rascunho +
                ", is_categoria=" + is_categoria +
                ", categoria=" + categoria +
                ", secao=" + secao +
                ", autor=" + autor +
                ", tags=" + tags +
                '}';
    }
}
