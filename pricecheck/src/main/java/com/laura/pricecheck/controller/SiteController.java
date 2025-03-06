package com.laura.pricecheck.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.laura.pricecheck.dto.CreateSiteResponseDTO;
import com.laura.pricecheck.model.Produto;
import com.laura.pricecheck.model.Site;
import com.laura.pricecheck.repository.ProdutoRepository;
import com.laura.pricecheck.repository.SiteRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Controller
@RequestMapping("/site")
public class SiteController {
	@Autowired
	private SiteRepository siteRepository;
	@Autowired
	private ProdutoRepository produtoRepository;

	@Operation(description = "Cria e popula o site baseado no id do item que foi passado.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Site criado com sucesso"),
			@ApiResponse(responseCode = "404", description = "Produto com o ID fornecido não foi encontrado"),
			@ApiResponse(responseCode = "400", description = "Erro na criação do site devido a dados inválidos") })
	@PostMapping("/create/{idProduto}")
	public ResponseEntity<?> create(@RequestBody CreateSiteResponseDTO body, @PathVariable Long idProduto) {
		Produto produto = produtoRepository.findById(idProduto).orElseThrow(
				() -> new IllegalArgumentException("Produto com o id " + idProduto + " não foi encontrado"));

		Site site = new Site();
		site.setIdProduto(produto);
		site.setNomeSite(body.nomeSite());
		site.setTagSite(body.tagSite());
		site.setUrlSite(body.urlSite());

		this.siteRepository.save(site);

		return ResponseEntity.ok(new CreateSiteResponseDTO(site.getIdProduto(), site.getNomeSite(), site.getIdSite(),
				site.getUrlSite(), site.getTagSite()));
	}

	@Operation(description = "Mostra todos os sites criados.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retorna uma lista com todos os sites encontrados"),
			@ApiResponse(responseCode = "500", description = "Erro interno no servidor ao listar os sites") })
	@GetMapping
	public ResponseEntity<List<Site>> getSite() {
		return ResponseEntity.ok(siteRepository.findAll());
	}
}
