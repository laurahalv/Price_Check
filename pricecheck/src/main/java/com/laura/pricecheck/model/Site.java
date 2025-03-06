package com.laura.pricecheck.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "site")
public class Site {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_site")
	private Long idSite;

	@Column(name = "nome_site", nullable = false, length = 255)
	private String nomeSite;

	@Column(name = "url_site", nullable = false, length = 255)
	private String urlSite;

	@Column(name = "tag_site", nullable = false, length = 255)
	private String tagSite;

	@ManyToOne
	@JoinColumn(name = "id_produto", nullable = false)
	private Produto idProduto;
}
