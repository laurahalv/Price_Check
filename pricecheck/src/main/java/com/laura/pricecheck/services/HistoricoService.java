package com.laura.pricecheck.services;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.laura.pricecheck.model.Historico;
import com.laura.pricecheck.model.Site;
import com.laura.pricecheck.repository.HistoricoRepository;
import com.laura.pricecheck.repository.SiteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HistoricoService {
	private final HistoricoRepository historicoRepository;
	private final SiteRepository siteRepository;

	public void pegarDados() throws IOException {
		List<Site> sites = siteRepository.findAll();
		for (Site site : sites) {
			try {
				String urlSite = site.getUrlSite();
				String tagSite = site.getTagSite();

				Document doc = Jsoup.connect(urlSite).userAgent("Mozilla/5.0").timeout(10000).get();

				Elements elements = doc.getElementsByClass(tagSite);

				if (!elements.isEmpty()) {
					String precoStr = elements.first().text();

					try {
						String precoLimpo = precoStr.replaceAll("[^\\d.,]", "");
						precoLimpo = precoLimpo.replaceAll("\\.", "");
						precoLimpo = precoLimpo.replace(",", ".");

						double preco = Double.parseDouble(precoLimpo);

						Historico historico = new Historico();
						historico.setIdSite(site);
						historico.setDiaVerificacao(LocalDate.now());
						historico.setPreco(preco);
						historicoRepository.save(historico);

					} catch (NumberFormatException e) {
						System.out.println("Erro ao processar o valor: " + precoStr);
						e.printStackTrace();
					}
				} else {
					System.out.println("Nenhum elemento encontrado para o seletor: " + tagSite);
				}
			} catch (Exception e) {
				System.out.println("Erro ao processar o site " + site.getNomeSite() + ": " + e.getMessage());
				e.printStackTrace();
			}
		}
		System.out.println("-----------------------------------------");
		System.out.println("Historico de Preço");
		System.out.println("-----------------------------------------");

		List<Historico> historicosExistentes = historicoRepository.findAll();

		if (historicosExistentes.isEmpty()) {
			System.out.println("Nenhum dado encontrado no histórico.");
		} else {
			Map<String, List<Historico>> historicosPorProduto = historicosExistentes.stream()
					.collect(Collectors.groupingBy(h -> h.getIdSite().getIdProduto().getNomeProduto()));

			for (Map.Entry<String, List<Historico>> entry : historicosPorProduto.entrySet()) {
				String nomeProduto = entry.getKey();
				List<Historico> historicos = entry.getValue();

				System.out.println("\nProduto: " + nomeProduto);
				System.out.println("-----------------------------------------");
				System.out.println("ID\tSite\tData\tPreço");

				for (Historico historico : historicos) {
					System.out.println(historico.getId() + "\t" + historico.getIdSite().getNomeSite() + "\t"
							+ historico.getDiaVerificacao() + "\t" + "R$ "
							+ String.format("%.2f", historico.getPreco()));
				}
			}
		}

	}

}