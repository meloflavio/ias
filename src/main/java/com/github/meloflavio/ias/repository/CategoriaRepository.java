package com.github.meloflavio.ias.repository;

import com.github.meloflavio.ias.model.Categoria;
import com.github.meloflavio.ias.model.Curso;
import com.github.meloflavio.ias.util.Abstracts.AbstractRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoriaRepository extends AbstractRepository<Categoria, Integer> {
    @Query("SELECT c FROM  Categoria c " +
            " WHERE upper(c.descricao) like upper(CONCAT('%',:desc,'%'))")
    List<Categoria> findByDescricao(@Param("desc") String descricao);

    @Query("SELECT c FROM  Categoria c " +
            " WHERE upper(c.descricao) like upper(CONCAT('%',:desc,'%'))")
    Categoria findOneByDescricao(@Param("desc") String descricao);

    @Query("SELECT c FROM  Categoria c " +
            " WHERE upper(c.descricao) not like upper('%pares%')")
    List<Categoria> findAllNotPares();

}
