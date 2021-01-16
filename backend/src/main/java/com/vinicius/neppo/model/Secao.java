package com.vinicius.neppo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "secoes")
public class Secao
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(name = "nome")
    @Length(max = 100)
    private String nome;

    @JsonIgnore
    @NotNull
    @ManyToOne()
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    public Secao() {}

    public Secao(long id, @Length(max = 100) String nome, @NotNull Categoria categoria)
    {
        Id = id;
        this.nome = nome;
        this.categoria = categoria;
    }

    public long getId()
    {
        return Id;
    }

    public void setId(long id)
    {
        Id = id;
    }

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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Secao secao = (Secao) o;
        return Id == secao.Id &&
                Objects.equals(nome, secao.nome) &&
                Objects.equals(categoria, secao.categoria);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(Id, nome, categoria);
    }

    @Override
    public String toString()
    {
        return "Secao{" +
                "Id=" + Id +
                ", nome='" + nome + '\'' +
                ", categoria=" + categoria +
                '}';
    }
}
