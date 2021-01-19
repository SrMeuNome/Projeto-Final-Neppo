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
import java.util.List;

@Transactional(readOnly = false)
public interface ArtigoRepository extends JpaRepository<Artigo, Long>
{
    List<Artigo> findAllByRascunhoFalse(Pageable pageable);

    List<Artigo> findAllByRascunhoFalseAndTagsIn(Collection<Tag> tags, Pageable pageable);

    List<Artigo> findAllByTituloContainingIgnoreCaseOrConteudoContainingIgnoreCaseAndRascunhoFalseAndTagsIn(String titulo, String conteudo, Collection<Tag> tags, Pageable pageable);

    List<Artigo> findAllByTituloContainingIgnoreCaseOrConteudoContainingIgnoreCaseAndRascunhoFalse(String titulo, String conteudo, Pageable pageable);

    List<Artigo> findAllByTituloContainingIgnoreCaseOrConteudoContainingIgnoreCaseAndTagsIn(@Param("titulo") String titulo, @Param("conteudo") String conteudo, @Param("tags") Collection<Tag> tags, Pageable pageable);

    List<Artigo> findAllByTituloContainingIgnoreCaseOrConteudoContainingIgnoreCase(String titulo, String conteudo, Pageable pageable);

    List<Artigo> findAllByTagsIn(Collection<Tag> tags, Pageable pageable);

    /*@Query(value = "SELECT a.* FROM artigos a, artigo_tag b WHERE UPPER(a.titulo) LIKE UPPER('%:texto%') or UPPER(a.conteudo) LIKE UPPER('%:texto%') AND a.id = b.id_artigo AND b.id_tag in (:tags) GROUP BY a.id;",
            countQuery = "SELECT COUNT(a.*) FROM artigos a, artigo_tag b WHERE UPPER(a.titulo) LIKE UPPER('%:texto%') or UPPER(a.conteudo) LIKE UPPER('%:texto%') AND a.id = b.id_artigo AND b.id_tag in (:tags) GROUP BY a.id;",
            nativeQuery = true)
    List<Artigo> findAllArtigosByTextAndTags(@Param("texto") String texto, @Param("tags") Collection<Long> tags, Pageable pageable);
     AND tags.id in :tags
     , @Param("tags") Collection<Long> tags

     ,
    countQuery = "SELECT count(a) FROM Artigo a WHERE (UPPER(a.titulo) LIKE UPPER(:texto) OR UPPER(a.conteudo) LIKE UPPER(:texto)) AND tags in :tags"
     */

    @Query(value = "SELECT a FROM Artigo a JOIN a.tags t WHERE (UPPER(a.titulo) LIKE UPPER(:texto) OR UPPER(a.conteudo) LIKE UPPER(:texto)) AND t in :tags GROUP BY a.id")
    Page<Artigo> findAllArtigosByParameters(@Param("texto") String texto, @Param("tags") Collection<Tag> tags, Pageable pageable);
}
