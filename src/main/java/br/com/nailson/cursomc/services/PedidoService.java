package br.com.nailson.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.nailson.cursomc.domain.Cliente;
import br.com.nailson.cursomc.domain.ItemPedido;
import br.com.nailson.cursomc.domain.PagamentoComBoleto;
import br.com.nailson.cursomc.domain.Pedido;
import br.com.nailson.cursomc.domain.enums.EstadoPagamento;
import br.com.nailson.cursomc.repositories.ItemPedidoRepository;
import br.com.nailson.cursomc.repositories.PagamentoRepository;
import br.com.nailson.cursomc.repositories.PedidoRepository;
import br.com.nailson.cursomc.security.UserSS;
import br.com.nailson.cursomc.services.exception.AuthorizationException;
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

	@Autowired
	private ClienteService cliService;

	@Autowired
	private EmailService emailService;

	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setCliente(cliService.find(obj.getCliente().getId()));
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoBoleto(pagto, obj.getInstante());
		}
		obj = repo.save(obj);
		repoPagto.save(obj.getPagamento());

		for (ItemPedido i : obj.getItens()) {
			i.setDesconto(0.0);
			i.setProduto(prodService.find(i.getProduto().getId()));
			i.setPreco(i.getProduto().getPreco());
			i.setPedido(obj);
		}

		itemRepo.saveAll(obj.getItens());
		System.out.println(obj);
		emailService.sendOrderConfirmationHtmlEmail(obj);
		return obj;
	}

	public Page<Pedido> findPages(Integer page, Integer linePerPage, String direction, String orderBy) {

		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Não autorizado");
		}

		PageRequest pageReq = PageRequest.of(page, linePerPage, Direction.fromString(direction), orderBy);
		Cliente cli = cliService.find(user.getId());

		return repo.findByCliente(cli, pageReq);
	}
}
