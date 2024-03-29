package com.gestaosimples.servico.repositories;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.gestaosimples.servico.domain.Categoria;
import com.gestaosimples.servico.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    //@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.Categorias cat WHERE obj.nome LIKE %:nome% AND cat in :categorias")
    //@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
    //Page<Produto> search(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias, Pageable pageRequest);

    @Transactional(readOnly = true)
    Page<Produto> findDistinctByNomeContainingIgnoreCaseAndCategoriasIn(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias,
        Pageable pageRequest);

    @Transactional(readOnly = true)
    Page<Produto> findDistinctByNomeContainingIgnoreCase(@Param("nome") String nome, Pageable pageRequest);

}
