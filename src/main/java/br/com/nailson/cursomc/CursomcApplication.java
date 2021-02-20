package br.com.nailson.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.nailson.cursomc.domain.Categoria;
import br.com.nailson.cursomc.domain.Cidade;
import br.com.nailson.cursomc.domain.Estado;
import br.com.nailson.cursomc.domain.Produto;
import br.com.nailson.cursomc.repositories.CategoriaRepository;
import br.com.nailson.cursomc.repositories.CidadeRepository;
import br.com.nailson.cursomc.repositories.EstadoRepository;
import br.com.nailson.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	@Autowired
	private CategoriaRepository catRepo;
	@Autowired
	private ProdutoRepository prodRepo;
	@Autowired
	private CidadeRepository cidRepo;
	@Autowired
	private EstadoRepository estRepo;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		catRepo.saveAll(Arrays.asList(cat1, cat2));
		prodRepo.saveAll(Arrays.asList(p1, p2,p3));

		Estado e1 = new Estado(null, "Minas Gerais"); 
		Estado e2 = new Estado(null, "São Paulo"); 
		
		Cidade ci1 = new Cidade(null, "Uberlandia", e1);
		Cidade ci2 = new Cidade(null, "São Paulo", e2);
		Cidade ci3 = new Cidade(null, "Campinas", e2);
		
		
		e1.getCidades().addAll(Arrays.asList(ci1));
		e2.getCidades().addAll(Arrays.asList(ci2, ci3));
		
		estRepo.saveAll(Arrays.asList(e1, e2));
		cidRepo.saveAll(Arrays.asList(ci1, ci2, ci3));
	}

}
