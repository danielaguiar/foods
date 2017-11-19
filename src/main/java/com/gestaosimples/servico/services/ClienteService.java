package com.gestaosimples.servico.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gestaosimples.servico.domain.Cliente;
import com.gestaosimples.servico.repositories.ClienteRepository;
import com.gestaosimples.servico.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    public Cliente buscar(Long id) {
        Cliente obj = repo.findOne(id);
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não econtrado! id: " + id + ", " + ClienteService.class.getName());
        }
        return obj;
    }

}
