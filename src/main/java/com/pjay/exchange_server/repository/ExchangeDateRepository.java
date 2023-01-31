package com.pjay.exchange_server.repository;

import com.pjay.exchange_server.model.entity.ExchangeDateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ExchangeDateRepository extends JpaRepository<ExchangeDateEntity, Long> {

    @Query(value = "select * from tb_exchange_date ORDER BY date DESC LIMIT 1", nativeQuery = true)
    Optional<ExchangeDateEntity> findByRecentData();

}
