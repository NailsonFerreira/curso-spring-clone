package br.com.nailson.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nailson.cursomc.domain.Pagamento;
import br.com.nailson.cursomc.repositories.PagamentoRepository;
import br.com.nailson.cursomc.services.exception.ObjectNotFoundException;


@Service
public class PagamentoService {

	@Autowired
	private PagamentoRepository repo;
	
	public Pagamento find(Integer id) {
		Optional<Pagamento> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Pagamento.class.getName()));
	}
	
}
