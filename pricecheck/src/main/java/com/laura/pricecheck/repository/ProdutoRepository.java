package com.laura.pricecheck.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laura.pricecheck.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
