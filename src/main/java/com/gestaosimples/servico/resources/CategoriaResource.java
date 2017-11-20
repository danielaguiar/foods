package com.gestaosimples.servico.resources;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.gestaosimples.servico.domain.Categoria;
import com.gestaosimples.servico.domain.dto.CategoriaDTO;
import com.gestaosimples.servico.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Long id) {
        Categoria categoria = service.find(id);
        return ResponseEntity.ok().body(categoria);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Categoria>> findAll() {
        List<Categoria> categoria = service.findAll();
        return ResponseEntity.ok().body(categoria);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public Page<Categoria> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page, //
        @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, //
        @RequestParam(value = "orderby", defaultValue = "nome") String orderby, //
        @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        return service.findPage(page, linesPerPage, orderby, direction);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO cateogria) {
        Categoria obj = service.insert(service.fromDTO(cateogria));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Categoria cateogria) {
        cateogria.setId(id);
        service.update(cateogria);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();

    }

}
