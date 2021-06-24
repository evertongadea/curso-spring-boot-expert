package com.github.evertongadea.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
	
	private Integer idCliente;
	private BigDecimal total;
	private List<ItemPedidoDTO> items;

}
