package com.github.evertongadea.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.evertongadea.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
