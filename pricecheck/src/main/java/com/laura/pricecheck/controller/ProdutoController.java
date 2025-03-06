package com.laura.pricecheck.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.laura.pricecheck.dto.CreateProdutoResponseDTO;
import com.laura.pricecheck.model.Produto;
import com.laura.pricecheck.repository.ProdutoRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Controller
@RequestMapping("/produto")
public class ProdutoController {
	@Autowired
	private ProdutoRepository produtoRepository;

	@Operation(description = "Cria um produto")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Produto criado com sucesso"), })
	@PostMapping("/criar")
	public ResponseEntity<?> create(@RequestBody CreateProdutoResponseDTO body) {
		Produto produto = new Produto();

		produto.setNomeProduto(body.nomeProduto());

		this.produtoRepository.save(produto);

		return ResponseEntity.ok(new CreateProdutoResponseDTO(produto.getIdProduto(), produto.getNomeProduto()));
	}

	@Operation(description = "Exibe todos os produtos salvos")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retorna os dados dos produtos salvos"), })
	@GetMapping
	public ResponseEntity<List<Produto>> getProduto() {
		return ResponseEntity.ok(produtoRepository.findAll());
	}
}
