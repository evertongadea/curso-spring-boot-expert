package com.github.evertongadea.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacoesItemPedidoDTO {

	private String descricaoProduto;
	private BigDecimal precoUnitadio;
	private Integer quantidade;
	
}
