package com.gestaosimples.servico.services;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gestaosimples.servico.domain.ItemPedido;
import com.gestaosimples.servico.domain.PagamentoComBoleto;
import com.gestaosimples.servico.domain.Pedido;
import com.gestaosimples.servico.domain.enuns.EstadoPagamento;
import com.gestaosimples.servico.repositories.ItemPedidoRepository;
import com.gestaosimples.servico.repositories.PagamentoRepository;
import com.gestaosimples.servico.repositories.PedidoRepository;
import com.gestaosimples.servico.repositories.ProdutoRepository;
import com.gestaosimples.servico.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repo;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public Pedido find(Long id) {
        Pedido obj = repo.findOne(id);
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não econtrado! id: " + id + ", " + PedidoService.class.getName());
        }
        return obj;
    }

    public Pedido insert(Pedido pedido) {

        pedido.setId(null);
        pedido.setInstante(new Date());
        pedido.getPagamento().setTipo(EstadoPagamento.P);
        pedido.getPagamento().setPedido(pedido);
        if (pedido.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto boleto = (PagamentoComBoleto) pedido.getPagamento();
            boletoService.preencherPagamentoComBoleto(boleto, pedido.getInstante());
        }
        pedido = repo.save(pedido);
        pagamentoRepository.save(pedido.getPagamento());

        for (ItemPedido ip : pedido.getItens()) {
            ip.setDesconto(0.0);
            ip.setProduto(produtoRepository.findOne(ip.getId().getProduto().getId()));
            ip.setPreco(ip.getProduto().getPreço());
        }
        itemPedidoRepository.save(pedido.getItens());

        return repo.save(pedido);

    }

}
