package br.com.nailson.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.nailson.cursomc.domain.Categoria;
import br.com.nailson.cursomc.domain.Cidade;
import br.com.nailson.cursomc.domain.Cliente;
import br.com.nailson.cursomc.domain.Endereco;
import br.com.nailson.cursomc.domain.Estado;
import br.com.nailson.cursomc.domain.ItemPedido;
import br.com.nailson.cursomc.domain.Pagamento;
import br.com.nailson.cursomc.domain.PagamentoComBoleto;
import br.com.nailson.cursomc.domain.PagamentoComCartao;
import br.com.nailson.cursomc.domain.Pedido;
import br.com.nailson.cursomc.domain.Produto;
import br.com.nailson.cursomc.domain.enums.EstadoPagamento;
import br.com.nailson.cursomc.domain.enums.TipoCliente;
import br.com.nailson.cursomc.repositories.CategoriaRepository;
import br.com.nailson.cursomc.repositories.CidadeRepository;
import br.com.nailson.cursomc.repositories.ClienteRepository;
import br.com.nailson.cursomc.repositories.EnderecoRepository;
import br.com.nailson.cursomc.repositories.EstadoRepository;
import br.com.nailson.cursomc.repositories.ItemPedidoRepository;
import br.com.nailson.cursomc.repositories.PagamentoRepository;
import br.com.nailson.cursomc.repositories.PedidoRepository;
import br.com.nailson.cursomc.repositories.ProdutoRepository;

@Service
public class DBService {
	
	@Autowired
	private CategoriaRepository catRepo;
	@Autowired
	private ProdutoRepository prodRepo;
	@Autowired
	private CidadeRepository cidRepo;
	@Autowired
	private EstadoRepository estRepo;
	@Autowired
	private ClienteRepository cliRepo;
	@Autowired
	private EnderecoRepository endeRepo;
	@Autowired
	private PedidoRepository pedRepo;
	@Autowired
	private PagamentoRepository pagRepo;
	@Autowired
	private ItemPedidoRepository ipRepo;
	
	@Autowired
	private BCryptPasswordEncoder passEncoder;

	public void instanciateDatabase() throws ParseException {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Banho");
		Categoria cat4 = new Categoria(null, "Mesa");
		Categoria cat5 = new Categoria(null, "Papelaria");
		Categoria cat6 = new Categoria(null, "Moveis");
		Categoria cat7 = new Categoria(null, "Brinquedos");
		Categoria cat8 = new Categoria(null, "Moda");
		Categoria cat9 = new Categoria(null, "Farmacia");
		Categoria cat10 = new Categoria(null, "Limpeza");
		Categoria cat11 = new Categoria(null, "Organização");
		Categoria cat12 = new Categoria(null, "Eletronicos");
		Categoria cat13 = new Categoria(null, "Perfumaria");
		
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "Vassoura", 10.00);
		Produto p5 = new Produto(null, "Rodo", 10.00);
		Produto p6 = new Produto(null, "Espanador", 5.00);
		Produto p7 = new Produto(null, "Toalha", 80.00);
		Produto p8 = new Produto(null, "Lapis", 80.00);
		Produto p9 = new Produto(null, "Caneta", 80.00);
		Produto p10 = new Produto(null, "Caderno", 80.00);
		Produto p11 = new Produto(null, "Pokemon", 80.00);
		Produto p12 = new Produto(null, "Digimon", 80.00);
		Produto p13 = new Produto(null, "Mesa", 80.00);
		Produto p14 = new Produto(null, "Boné", 80.00);
		Produto p15 = new Produto(null, "Saia", 80.00);
		Produto p16 = new Produto(null, "Blusa", 80.00);
		Produto p17 = new Produto(null, "Celular", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		cat3.getProdutos().addAll(Arrays.asList(p7));
		cat5.getProdutos().addAll(Arrays.asList(p8,p9,p10));
		cat6.getProdutos().addAll(Arrays.asList(p13));
		cat7.getProdutos().addAll(Arrays.asList(p11,p12));
		cat8.getProdutos().addAll(Arrays.asList(p14,p15,p16));
		cat10.getProdutos().addAll(Arrays.asList(p4,p5,p6));
		cat12.getProdutos().addAll(Arrays.asList(p17));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		catRepo.saveAll(Arrays.asList(cat1, cat2, cat3,cat4,cat5,cat6,cat7,cat8,cat9,cat10,cat11,cat12,cat13));
		prodRepo.saveAll(Arrays.asList(p1, p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12,p13,p14,p15,p16,p17));

		Estado e1 = new Estado(null, "Minas Gerais"); 
		Estado e2 = new Estado(null, "São Paulo"); 
		
		Cidade ci1 = new Cidade(null, "Uberlandia", e1);
		Cidade ci2 = new Cidade(null, "São Paulo", e2);
		Cidade ci3 = new Cidade(null, "Campinas", e2);
		
		
		e1.getCidades().addAll(Arrays.asList(ci1));
		e2.getCidades().addAll(Arrays.asList(ci2, ci3));
		
		estRepo.saveAll(Arrays.asList(e1, e2));
		cidRepo.saveAll(Arrays.asList(ci1, ci2, ci3));
		
		Cliente cli1 = new Cliente(null, "Maria", "nah@hotmail.com", "125.375.330-03",TipoCliente.PESSOAFISICA, passEncoder.encode("2110"));
		Cliente cli2 = new Cliente(null, "Nay", "nailson_ferreira@hotmail.com", "854.101.460-69",TipoCliente.PESSOAFISICA, passEncoder.encode("2110"));
		cli1.getTelefones().addAll(Arrays.asList("1234-5678", "0000-0000"));
		cli2.getTelefones().addAll(Arrays.asList("0000-1111", "0000-2222"));
		
		Endereco en1 = new Endereco(null, "Rua das Flores", "300", "Apto. 23", "Hamptons","55565-000", cli1, ci1);
		Endereco en2 = new Endereco(null, "Rua da Arurora", "300", "Apto. 24", "WestChester","55565-000", cli1, ci2);
		Endereco en3 = new Endereco(null, "Rua Julio Belo", "454", "Casa", "WestChester","55565-000", cli2, ci2);
		
		cli1.getEnderecos().addAll(Arrays.asList(en1,en2));
		cli2.getEnderecos().addAll(Arrays.asList(en3));
		
		cliRepo.saveAll(Arrays.asList(cli1, cli2));
		endeRepo.saveAll(Arrays.asList(en1,en2, en3));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("01/10/1991 17:00"),cli1, en1);
		Pedido ped2 = new Pedido(null, sdf.parse("01/10/1999 16:00"),cli1, en2);
		
		
		Pagamento pag1=  new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pag1);
		Pagamento pag2=  new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("11/03/2015 00:00"), null);
		ped2.setPagamento(pag2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedRepo.saveAll(Arrays.asList(ped1, ped2));
		pagRepo.saveAll(Arrays.asList(pag1, pag2));
		
		
		ItemPedido it1 = new ItemPedido(ped1, p1, 0.0,1,2000.0);		
		ItemPedido it2 = new ItemPedido(ped1, p3, 0.0,2,80.0);
		ItemPedido it3 = new ItemPedido(ped2, p2, 100.0,1,800.0);
		
		ped1.getItens().addAll(Arrays.asList(it1,it2));
		ped2.getItens().addAll(Arrays.asList(it3));
		
		p1.getItens().addAll(Arrays.asList(it1));
		p2.getItens().addAll(Arrays.asList(it3));
		p3.getItens().addAll(Arrays.asList(it2));
		
		ipRepo.saveAll(Arrays.asList(it1,it2,it3));
	}
}
