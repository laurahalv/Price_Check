package com.laura.pricecheck.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laura.pricecheck.model.Historico;

public interface HistoricoRepository extends JpaRepository<Historico, Long> {

}
