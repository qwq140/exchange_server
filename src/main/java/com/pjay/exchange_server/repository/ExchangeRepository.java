package com.pjay.exchange_server.repository;

import com.pjay.exchange_server.model.entity.ExchangeApiDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRepository extends JpaRepository<ExchangeApiDataEntity, Long> {
}
