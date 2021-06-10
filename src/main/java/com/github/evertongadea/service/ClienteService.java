package com.github.evertongadea.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.evertongadea.model.Cliente;
import com.github.evertongadea.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;

//	public void salvarCliente (Cliente cliente) {
//		if(validarCliente(cliente))
//			this.clienteRepository.persistir(cliente);
//	}
	
	public boolean validarCliente (Cliente cliente) {
		//TODO validações
		return true;
	}
}
