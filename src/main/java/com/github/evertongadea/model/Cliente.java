package com.github.evertongadea.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Integer idCliente;
    private String nome;

    @OneToMany(mappedBy = "cliente")
    private Set<Pedido> pedidos;
    
    public Cliente() {
	}

	public Cliente(String nome) {
		this.nome = nome;
	}
	
	public Cliente(Integer idCliente, String nome) {
		this.idCliente = idCliente;
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return "Cliente [idCliente=" + this.getIdCliente() + ", nome=" + this.getNome() + "]";
	}
    
    
}
