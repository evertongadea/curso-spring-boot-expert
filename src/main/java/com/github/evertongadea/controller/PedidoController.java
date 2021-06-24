package com.github.evertongadea.controller;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.github.evertongadea.dto.InformacoesItemPedidoDTO;
import com.github.evertongadea.dto.InformacoesPedidoDTO;
import com.github.evertongadea.dto.PedidoDTO;
import com.github.evertongadea.model.ItemPedido;
import com.github.evertongadea.model.Pedido;
import com.github.evertongadea.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

	private PedidoService service;

	public PedidoController(PedidoService service) {
		this.service = service;
	}
	
	@PostMapping("/salvar")
	@ResponseStatus(HttpStatus.CREATED)
	public Integer salvar(@RequestBody PedidoDTO dto) {
		Pedido pedido = service.salvar(dto);
		return pedido.getIdPedido();
	}
	
	@GetMapping("/buscar/{idPedido}")
	public InformacoesPedidoDTO obeterInPedido(@PathVariable Integer idPedido) {
		return service
				.obeterPedidoCompleto(idPedido)
				.map( p -> converter(p))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado!"));
	}
	
	private InformacoesPedidoDTO converter(Pedido pedido) {
		return InformacoesPedidoDTO
				.builder()
				.codigo(pedido.getIdPedido())
				.cpf(pedido.getCliente().getCpf())
				.dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")))
				.nomeCliente(pedido.getCliente().getNome())
				.total(pedido.getTotal())
				.status(pedido.getStatus().name())
				.items(converter(pedido.getItensPedido()))
				.build();
	}
	
	private List<InformacoesItemPedidoDTO> converter(List<ItemPedido> itens) {
		if(itens.isEmpty()) {
			return Collections.emptyList();
		}
		
		return itens
				.stream()
				.map( item -> InformacoesItemPedidoDTO
						.builder()
						.descricaoProduto(item.getProduto().getDescricao())
						.precoUnitadio(item.getProduto().getPreco())
						.quantidade(item.getQuantidade())
						.build()
					).collect(Collectors.toList());
	}
	
}
