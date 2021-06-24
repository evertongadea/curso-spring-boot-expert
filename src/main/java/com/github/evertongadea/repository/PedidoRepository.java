package com.github.evertongadea.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.github.evertongadea.model.Cliente;
import com.github.evertongadea.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
	
	List<Pedido> findByCliente(@Param("cliente") Cliente cliente);

	@Query(value = "SELECT p FROM Pedido p LEFT JOIN FETCH p.itensPedido WHERE p.idPedido = :idPedido")
	Optional<Pedido> findByIdPedido(@Param("idPedido") Integer idPedido);
}
