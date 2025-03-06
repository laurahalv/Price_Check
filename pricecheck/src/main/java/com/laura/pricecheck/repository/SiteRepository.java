package com.laura.pricecheck.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laura.pricecheck.model.Site;

public interface SiteRepository extends JpaRepository<Site, Long> {
}
