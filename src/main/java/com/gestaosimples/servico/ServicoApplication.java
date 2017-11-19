package com.gestaosimples.servico;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.gestaosimples.servico.domain.Categoria;
import com.gestaosimples.servico.domain.Cidade;
import com.gestaosimples.servico.domain.Cliente;
import com.gestaosimples.servico.domain.Endereco;
import com.gestaosimples.servico.domain.Estado;
import com.gestaosimples.servico.domain.Pagamento;
import com.gestaosimples.servico.domain.PagamentoComBoleto;
import com.gestaosimples.servico.domain.PagamentoComCartao;
import com.gestaosimples.servico.domain.Pedido;
import com.gestaosimples.servico.domain.Produto;
import com.gestaosimples.servico.domain.enuns.EstadoPagamento;
import com.gestaosimples.servico.domain.enuns.TipoCliente;
import com.gestaosimples.servico.repositories.CategoriaRepository;
import com.gestaosimples.servico.repositories.CidadeRepository;
import com.gestaosimples.servico.repositories.ClienteRepository;
import com.gestaosimples.servico.repositories.EnderecoRepository;
import com.gestaosimples.servico.repositories.EstadoRepository;
import com.gestaosimples.servico.repositories.PagamentoRepository;
import com.gestaosimples.servico.repositories.PedidoRepository;
import com.gestaosimples.servico.repositories.ProdutoRepository;

@SpringBootApplication
public class ServicoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ServicoApplication.class, args);
    }

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    CidadeRepository cidadeRepository;

    @Autowired
    EstadoRepository estadoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    PagamentoRepository pagamentoRepository;

    @Override
    public void run(String... args) throws Exception {
        Categoria cat1 = new Categoria("Informatica");
        Categoria cat2 = new Categoria("Escritório");

        Produto p1 = new Produto("Computador", 2000.00);
        Produto p2 = new Produto("Impressora", 800.00);
        Produto p3 = new Produto("Mouse", 80.00);

        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProdutos().addAll(Arrays.asList(p2));

        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
        p3.getCategorias().addAll(Arrays.asList(cat1, cat1));

        Estado est1 = new Estado("Minas Gerias");
        Estado est2 = new Estado("São Paulo");

        Cidade c1 = new Cidade("Uberlandi", est1);
        Cidade c2 = new Cidade("Sao Paulo", est2);
        Cidade c3 = new Cidade("Sao Paulo", est2);

        est1.getCidades().addAll(Arrays.asList(c1));
        est2.getCidades().addAll(Arrays.asList(c2, c3));

        Cliente cliente1 = new Cliente("Daniel Aguiar", "bsb.aguiar@gmail.com", "65847580100", TipoCliente.F);
        Cliente cliente2 = new Cliente("Kaio Ferreira Aguiar", "kaioferreiraaguiar@gmail.com", "00000000001", TipoCliente.F);

        cliente1.getTelefones().addAll(Arrays.asList("00000000000", "0000000010101"));
        cliente2.getTelefones().addAll(Arrays.asList("2222222", "333333"));

        Endereco e1 = new Endereco("rua vlores", "teste", "teste", "adfasd", "72880576", cliente1, c1);
        Endereco e2 = new Endereco("endereco e2", "e2", "e2", "e2", "72880576", cliente1, c2);
        Endereco e3 = new Endereco("endereco e3", "e3", "e3", "e3", "72880576", cliente2, c3);

        cliente1.getEnderecos().addAll(Arrays.asList(e1, e2));
        cliente2.getEnderecos().addAll(Arrays.asList(e3));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");

        Pedido ped1 = new Pedido(sdf.parse("30/09/2017 12:12"), cliente1, e1);
        Pedido ped2 = new Pedido(sdf.parse("10/10/2017 19:32"), cliente1, e2);

        Pagamento pgto1 = new PagamentoComCartao(EstadoPagamento.Q, ped1, 6);
        ped1.setPagamento(pgto1);
        Pagamento pgto2 = new PagamentoComBoleto(EstadoPagamento.P, ped2, sdf.parse("11/12/2018 00:00"), null);
        ped2.setPagamento(pgto2);

        cliente1.getPedidos().addAll(Arrays.asList(ped1));
        cliente2.getPedidos().addAll(Arrays.asList(ped2));

        categoriaRepository.save(Arrays.asList(cat1, cat2));
        produtoRepository.save(Arrays.asList(p1, p2, p3));
        estadoRepository.save(Arrays.asList(est1, est2));
        cidadeRepository.save(Arrays.asList(c1, c2, c3));
        clienteRepository.save(Arrays.asList(cliente1, cliente2));
        enderecoRepository.save(Arrays.asList(e1, e2, e3));

        pedidoRepository.save(Arrays.asList(ped1, ped2));
        pagamentoRepository.save(Arrays.asList(pgto1, pgto2));

    }

}
