package com.gestaosimples.servico.resources;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.gestaosimples.servico.domain.Pedido;
import com.gestaosimples.servico.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource extends AbstractResource {

    @Autowired
    private PedidoService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Long id) {
        Pedido pedido = service.find(id);
        return ResponseEntity.ok().body(pedido);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody Pedido pedido) {
        Pedido obj = service.insert(pedido);
        return this.createResponse("/{id}", obj.getId());
    }

}
