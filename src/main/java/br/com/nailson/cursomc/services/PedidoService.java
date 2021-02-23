package br.com.nailson.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.nailson.cursomc.domain.ItemPedido;
import br.com.nailson.cursomc.domain.PagamentoComBoleto;
import br.com.nailson.cursomc.domain.Pedido;
import br.com.nailson.cursomc.domain.enums.EstadoPagamento;
import br.com.nailson.cursomc.repositories.ItemPedidoRepository;
import br.com.nailson.cursomc.repositories.PagamentoRepository;
import br.com.nailson.cursomc.repositories.PedidoRepository;
import br.com.nailson.cursomc.services.exception.ObjectNotFoundException;


@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository repoPagto;
	
	@Autowired
	private ProdutoService prodService;
	
	@Autowired
	private ItemPedidoRepository itemRepo;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoBoleto(pagto, obj.getInstante());
		}
		obj = repo.save(obj);
		repoPagto.save(obj.getPagamento());
		
		for(ItemPedido i : obj.getItens()) {
			i.setDesconto(0.0);
			i.setPreco(prodService.find(i.getProduto().getId()).getPreco());
			i.setPedido(obj);
		}
		
		itemRepo.saveAll(obj.getItens());
		
		return obj;
	}
}
