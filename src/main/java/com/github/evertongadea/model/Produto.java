package com.github.evertongadea.model;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idProduto;
	
	private String descricao;
	private BigDecimal preco;
	
	@JsonIgnore
	@OneToMany(mappedBy = "pedido")
	private Set<ItemPedido> itensPedido;
	
	public Produto() {
	}

	public Produto(String descricao, BigDecimal preco) {
		this.descricao = descricao;
		this.preco = preco;
	}

}
