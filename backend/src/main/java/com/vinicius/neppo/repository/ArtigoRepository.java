package com.vinicius.neppo.repository;

import com.vinicius.neppo.model.Artigo;
import com.vinicius.neppo.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Transactional(readOnly = false)
public interface ArtigoRepository extends JpaRepository<Artigo, Long>
{
    //Artigos geral
    @Query(value = "SELECT a FROM Artigo a WHERE UPPER(a.titulo) LIKE UPPER(:texto) OR UPPER(a.conteudo) LIKE UPPER(:texto) GROUP BY a.id")
    Page<Artigo> findAllArtigosByText(@Param("texto") String texto, Pageable pageable);

    @Query(value = "SELECT a FROM Artigo a JOIN a.tags t WHERE t in :tags GROUP BY a.id")
    Page<Artigo> findAllArtigosByTag(@Param("tags") Collection<Tag> tags, Pageable pageable);

    @Query(value = "SELECT a FROM Artigo a JOIN a.tags t WHERE (UPPER(a.titulo) LIKE UPPER(:texto) OR UPPER(a.conteudo) LIKE UPPER(:texto)) AND t in :tags GROUP BY a.id")
    Page<Artigo> findAllArtigosByParameters(@Param("texto") String texto, @Param("tags") Collection<Tag> tags, Pageable pageable);

    //Artigos publicados
    Page<Artigo> findAllByRascunhoFalse(Pageable pageable);

    @Query(value = "SELECT a FROM Artigo a WHERE (UPPER(a.titulo) LIKE UPPER(:texto) OR UPPER(a.conteudo) LIKE UPPER(:texto)) AND a.rascunho = false GROUP BY a.id")
    Page<Artigo> findAllArtigosPublicadosByText(@Param("texto") String texto, Pageable pageable);

    @Query(value = "SELECT a FROM Artigo a JOIN a.tags t WHERE t in :tags AND a.rascunho = false GROUP BY a.id")
    Page<Artigo> findAllArtigosPublicadosByTag(@Param("tags") Collection<Tag> tags, Pageable pageable);

    @Query(value = "SELECT a FROM Artigo a JOIN a.tags t WHERE (UPPER(a.titulo) LIKE UPPER(:texto) OR UPPER(a.conteudo) LIKE UPPER(:texto)) AND t in :tags AND a.rascunho = false GROUP BY a.id")
    Page<Artigo> findAllArtigosPublicadosByParameters(@Param("texto") String texto, @Param("tags") Collection<Tag> tags, Pageable pageable);
}