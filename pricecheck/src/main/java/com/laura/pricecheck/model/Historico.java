package com.laura.pricecheck.model;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@OpenAPIDefinition
@Data
@Entity
@Table(name = "historico")
public class Historico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_site", nullable = false)
	private Site idSite;

	@Column(name = "dia_verificacao", nullable = false)
	private LocalDate diaVerificacao;

	@Column(name = "preco", nullable = false)
	private double preco;
}
