package br.com.nailson.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.nailson.cursomc.domain.Categoria;
import br.com.nailson.cursomc.domain.Produto;
import br.com.nailson.cursomc.repositories.CategoriaRepository;
import br.com.nailson.cursomc.repositories.ProdutoRepository;
import br.com.nailson.cursomc.services.exception.ObjectNotFoundException;


@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository catRepo;
	
	public Produto find(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}
	
	public Page<Produto> search(String nome, List<Integer> ids,Integer page, Integer linePerPage, String direction, String orderBy){
		PageRequest pageReq= PageRequest.of(page, linePerPage,Direction.fromString(direction) , orderBy);
		
		List<Categoria> categorias = catRepo.findAllById(ids);
		
		
//		return repo.search(nome,categorias,pageReq);
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome,categorias,pageReq);
	}
}
