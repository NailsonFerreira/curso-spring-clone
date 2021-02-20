package br.com.nailson.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nailson.cursomc.domain.Pedido;

@Repository
public interface PedidoRepository  extends JpaRepository<Pedido, Integer>{

}
