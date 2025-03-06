package com.laura.pricecheck.dto;

import com.laura.pricecheck.model.Produto;

public record CreateSiteResponseDTO (Produto idProduto, String nomeSite, Long idSite, String urlSite, String tagSite){
}
