package br.com.nailson.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nailson.cursomc.domain.Pagamento;

@Repository
public interface PagamentoRepository  extends JpaRepository<Pagamento, Integer>{

}
