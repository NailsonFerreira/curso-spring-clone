package br.com.nailson.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nailson.cursomc.domain.Endereco;

@Repository
public interface EnderecoRepository  extends JpaRepository<Endereco, Integer>{

}
