package com.github.evertongadea.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Integer idCliente;
	
	@NotEmpty(message = "{campo.cliente.nome.obrigatorio}")
    private String nome;

	@Column(name = "cpf", length = 11)
	@NotEmpty(message = "{campo.cliente.cpf.obrigatorio}")
	@CPF(message = "{campo.cliente.cpf.invalido}")
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private Set<Pedido> pedidos;

	public Cliente(String nome) {
		this.nome = nome;
	}
	
	public Cliente(Integer idCliente, String nome) {
		this.idCliente = idCliente;
		this.nome = nome;
	}
	
	
	public Cliente(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}
	
	@Override
	public String toString() {
		return "Cliente [idCliente=" + this.getIdCliente() + ", nome=" + this.getNome() + "]";
	}
    
    
}
