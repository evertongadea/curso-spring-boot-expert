package com.github.evertongadea.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
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

import com.github.evertongadea.model.Produto;
import com.github.evertongadea.repository.ProdutoRepository;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository repository;
	
	@PostMapping("/salvar")
	@ResponseStatus(HttpStatus.CREATED)
	public Produto salvar(@RequestBody @Valid Produto produto) {
		return repository.save(produto);
	}

	@GetMapping("/buscar/{idProduto}")
	public Produto buscar(@PathVariable Integer idProduto) {
		return repository.findById(idProduto)
							.orElseThrow(() -> naoEncontrado());
	}
	
	@PutMapping("/atualizar/{idProduto}")
	public void atualizar(@PathVariable Integer idProduto, @RequestBody @Valid Produto produto) {
		repository.findById(idProduto)
					.map(prodEncontrado -> {
						produto.setIdProduto(prodEncontrado.getIdProduto());
						repository.save(produto);
						return produto;
					})
					.orElseThrow(() -> naoEncontrado());
	}
	
	@DeleteMapping("/deletar/{idProduto}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Integer idProduto) {
		repository.findById(idProduto)
				.map(produto -> {
					repository.delete(produto);
					return Void.TYPE;
				})
				.orElseThrow(() -> naoEncontrado());
	}
	
	
	@GetMapping("/listar")
	public List<Produto> listar(Produto produtoFilter) {
		ExampleMatcher matcher = ExampleMatcher.matching()
											.withIgnoreCase()
											.withStringMatcher(StringMatcher.CONTAINING);
		
		Example example = Example.of(produtoFilter, matcher);
		return repository.findAll(example);
	}
	
	
	private ResponseStatusException naoEncontrado() {
		return new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!");
	}
}
