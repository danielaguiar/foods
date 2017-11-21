package com.gestaosimples.servico.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.gestaosimples.servico.domain.Cidade;
import com.gestaosimples.servico.domain.Cliente;
import com.gestaosimples.servico.domain.Endereco;
import com.gestaosimples.servico.domain.dto.ClienteDTO;
import com.gestaosimples.servico.domain.dto.ClienteNewDTO;
import com.gestaosimples.servico.domain.enuns.TipoCliente;
import com.gestaosimples.servico.repositories.ClienteRepository;
import com.gestaosimples.servico.services.exceptions.DataIntegrityException;
import com.gestaosimples.servico.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    public Cliente find(Long id) {
        Cliente obj = repo.findOne(id);
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não econtrado! id: " + id + ", " + ClienteService.class.getName());
        }
        return obj;
    }

    public Cliente insert(Cliente categoria) {
        categoria.setId(null);
        return repo.save(categoria);
    }

    public Cliente update(Cliente cliente) {
        Cliente cli = find(cliente.getId());
        updataData(cliente, cli);
        return repo.save(cliente);
    }

    private void updataData(Cliente cliente, Cliente cli) {
        cli.setNome(cliente.getNome());
        cli.setEmail(cliente.getEmail());
    }

    public void delete(Long id) {
        find(id);
        try {
            repo.delete(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma categoria que tem produtos");
        }
    }

    public List<Cliente> findAll() {
        return repo.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderby, String direction) {
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderby);
        return repo.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO dto) {
        return new Cliente(dto.getNome(), dto.getEmail());
    }

    public Cliente fromDTO(ClienteNewDTO dto) {
        Cliente cli = new Cliente(dto.getNome(), dto.getEmail(), dto.getCpfOuCnpj(), TipoCliente.toEnum(dto.getTipo()));
        Endereco end =
            new Endereco(dto.getLogradouro(), dto.getNumero(), dto.getComplemento(), dto.getBairro(), dto.getCep(), cli, new Cidade(dto.getIdCidade()));
        cli.getEnderecos().add(end);
        cli.getTelefones().add(dto.getTelefone1());
        if (dto.getTelefone2() != null) {
            cli.getTelefones().add(dto.getTelefone2());
        }
        if (dto.getTelefone3() != null) {
            cli.getTelefones().add(dto.getTelefone3());
        }
        return cli;
    }
}
