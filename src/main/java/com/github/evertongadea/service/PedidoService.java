package com.github.evertongadea.service;

import java.util.Optional;

import com.github.evertongadea.dto.PedidoDTO;
import com.github.evertongadea.model.Pedido;

public interface PedidoService {
	public Pedido salvar(PedidoDTO dto);
	
	public Optional<Pedido> obeterPedidoCompleto(Integer idPedido);
}
