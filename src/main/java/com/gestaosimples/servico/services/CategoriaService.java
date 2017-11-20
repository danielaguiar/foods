package com.gestaosimples.servico.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gestaosimples.servico.domain.Categoria;
import com.gestaosimples.servico.repositories.CategoriaRepository;
import com.gestaosimples.servico.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    public Categoria find(Long id) {
        Categoria obj = repo.findOne(id);
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não econtrado! id: " + id + ", " + CategoriaService.class.getName());
        }
        return obj;
    }

    public Categoria insert(Categoria categoria) {
        categoria.setId(null);
        return repo.save(categoria);
    }

    public Categoria update(Categoria cateogria) {
        find(cateogria.getId());
        return repo.save(cateogria);
    }
}
