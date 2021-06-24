package com.github.evertongadea.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.github.evertongadea.dto.ItemPedidoDTO;
import com.github.evertongadea.dto.PedidoDTO;
import com.github.evertongadea.enums.StatusPedido;
import com.github.evertongadea.exception.RegraNegocioException;
import com.github.evertongadea.model.Cliente;
import com.github.evertongadea.model.ItemPedido;
import com.github.evertongadea.model.Pedido;
import com.github.evertongadea.model.Produto;
import com.github.evertongadea.repository.ClienteRepository;
import com.github.evertongadea.repository.ItemPedidoRepository;
import com.github.evertongadea.repository.PedidoRepository;
import com.github.evertongadea.repository.ProdutoRepository;
import com.github.evertongadea.service.PedidoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService{

	private final PedidoRepository repository;
	private final ClienteRepository cliRepository;
	private final ProdutoRepository prodRepository;
	private final ItemPedidoRepository itPedRepository;

	@Override
	@Transactional
	public Pedido salvar(PedidoDTO dto) {
		Cliente cliente = cliRepository
					.findByIdCliente(dto.getIdCliente())
					.orElseThrow(() -> new RegraNegocioException("Código de cliente inválido: "+dto.getIdCliente()));
		
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setTotal(dto.getTotal());
		pedido.setDataPedido(LocalDate.now());
		pedido.setStatus(StatusPedido.REALIZADO);
		repository.save(pedido);
		
		converteItems(pedido, dto.getItems());
		return pedido;
	}
	
	
	private List<ItemPedido> converteItems(Pedido pedido, List<ItemPedidoDTO> itemsDTO) {
		if(itemsDTO.isEmpty()) {
			throw new RegraNegocioException("Não é possível realizar um pedido sem items.");
		}
		
		return itemsDTO
				.stream()
				.map(dto -> {
					Produto produto = prodRepository
							.findById(dto.getIdProduto())
							.orElseThrow(() -> new RegraNegocioException("Código de produto inválido: "+dto.getIdProduto()));
					
					ItemPedido itemPedido = new ItemPedido();
					itemPedido.setPedido(pedido);
					itemPedido.setProduto(produto);
					itemPedido.setQuantidade(dto.getQuantidade());
					itPedRepository.save(itemPedido);
					
					return itemPedido;
				}).collect(Collectors.toList());
	}


	@Override
	public Optional<Pedido> obeterPedidoCompleto(Integer idPedido) {
		return repository.findByIdPedido(idPedido);
	}
}
