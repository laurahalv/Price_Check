package com.laura.pricecheck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.laura.pricecheck.services.HistoricoService;

@SpringBootApplication
public class PricecheckApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(PricecheckApplication.class, args);

		HistoricoService historicoService = context.getBean(HistoricoService.class);

		try {
			historicoService.pegarDados();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao executar pegarDados(): " + e.getMessage());
		}
	}
}
