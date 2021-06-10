package com.github.evertongadea.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.github.evertongadea.model.Cliente;
import com.github.evertongadea.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
	
	List<Pedido> findByCliente(@Param("cliente") Cliente cliente);

}
