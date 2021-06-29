package com.github.evertongadea.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.github.evertongadea.model.Cliente;
import com.github.evertongadea.repository.ClienteRepository;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository repository;

//	@GetMapping("/buscar/{idCliente}")
//	public ResponseEntity<Cliente> buscars(@PathVariable Integer idCliente) {
//		Optional<Cliente> cliente = cliRepository.findByIdCliente(idCliente);
//		
//		if(cliente.isPresent()) {
//			return ResponseEntity.ok(cliente.get());
//		}
//		return ResponseEntity.notFound().build();
//	}
	
	@GetMapping("/buscar/{idCliente}")
	public Cliente buscar(@PathVariable Integer idCliente) {
		return repository.findByIdCliente(idCliente)
							.orElseThrow(() -> naoEncontrado());
	}
	
	@PostMapping("/salvar")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente salvar(@RequestBody @Valid Cliente cliente) {
		return repository.save(cliente);
	}
	
	@DeleteMapping("/deletar/{idCliente}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void detelar(@PathVariable Integer idCliente) {
		repository.findByIdCliente(idCliente)
					.map(cli -> {
						repository.delete(cli); 
						return cli; 
						})
					.orElseThrow(() -> naoEncontrado());
	}
	
	@PutMapping("/atualizar/{idCliente}")
	public void atualizar(@PathVariable Integer idCliente, @RequestBody @Valid Cliente cliente) {
		repository.findByIdCliente(idCliente)
				.map(clienteEncontrado -> {
					cliente.setIdCliente(clienteEncontrado.getIdCliente());
					repository.save(cliente);
					return cliente;
				})
				.orElseThrow(() -> naoEncontrado());
	}
	
	@GetMapping("/listar")
	public List<Cliente> listar(Cliente clienteFilter) {
		ExampleMatcher matcher = ExampleMatcher
								.matching()
								.withIgnoreCase()
								.withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING );

		Example example = Example.of(clienteFilter, matcher);
		return repository.findAll(example);
	}
	
	private ResponseStatusException naoEncontrado() throws ResponseStatusException {
		return new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!");
	}
}
