package com.pjay.exchange_server.service;

import com.pjay.exchange_server.model.ExchangeApiDataEntity;
import com.pjay.exchange_server.model.ExchangeDateEntity;
import com.pjay.exchange_server.model.ExchangeDto;
import com.pjay.exchange_server.repository.ExchangeDateRepository;
import com.pjay.exchange_server.repository.ExchangeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExchangeService {

    private final ExchangeRepository exchangeRepository;
    private final ExchangeDateRepository exchangeDateRepository;

    @Transactional
    public void saveAll(List<ExchangeDto> dtoList){
        ExchangeDateEntity saveExchangeDate = exchangeDateRepository.save(
                ExchangeDateEntity.builder()
                        .date(LocalDateTime.now())
                        .build()
        );

        for(ExchangeDto dto : dtoList){
            exchangeRepository.save(dto.toEntity(saveExchangeDate));
//            saveExchangeDate.getDataList().add(exchangeApiDataEntity);
        }
    }
}
