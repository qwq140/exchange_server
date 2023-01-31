package com.pjay.exchange_server.repository;

import com.pjay.exchange_server.model.ExchangeDateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeDateRepository extends JpaRepository<ExchangeDateEntity, Long> {
}
