package com.github.evertongadea.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.github.evertongadea.validation.NotEmptyList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
	
	@NotNull(message = "{campo.pedido.cdcliente.obrigatorio}")
	private Integer idCliente;
	
	@NotNull(message = "{campo.pedido.total.obrigatorio}")
	private BigDecimal total;
	
	@NotEmptyList(message = "{campo.pedido.listaitens.obrigatorio}")
	private List<ItemPedidoDTO> items;

}
