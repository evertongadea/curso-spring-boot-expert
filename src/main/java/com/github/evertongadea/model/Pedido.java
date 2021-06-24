package com.github.evertongadea.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.github.evertongadea.enums.StatusPedido;

import lombok.Data;

@Data
@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idPedido;
	
	@ManyToOne
	@JoinColumn(name = "ID_CLIENTE")
	private Cliente cliente;
	
	private LocalDate dataPedido;
	
	@Column(name = "total", scale = 2, precision = 20)
	private BigDecimal total;
	
	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itensPedido;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private StatusPedido status; 

	@Override
	public String toString() {
		return "Pedido [idPedido=" + getIdPedido() + ", dataPedido=" + getDataPedido() + ", total=" + getTotal() + "]";
	}

	
	
	
}
