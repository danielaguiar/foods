package com.gestaosimples.servico.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gestaosimples.servico.domain.Pedido;
import com.gestaosimples.servico.repositories.PedidoRepository;
import com.gestaosimples.servico.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repo;

    public Pedido buscar(Long id) {
        Pedido obj = repo.findOne(id);
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não econtrado! id: " + id + ", " + PedidoService.class.getName());
        }
        return obj;
    }

}
