package com.pjay.exchange_server.service;

import com.pjay.exchange_server.model.dto.response.ExchangeDto;
import com.pjay.exchange_server.model.entity.ExchangeDateEntity;
import com.pjay.exchange_server.model.dto.response.ExchangeApiDto;
import com.pjay.exchange_server.repository.ExchangeDateRepository;
import com.pjay.exchange_server.repository.ExchangeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExchangeService {

    private final ExchangeRepository exchangeRepository;
    private final ExchangeDateRepository exchangeDateRepository;

    @Transactional
    public void saveAll(List<ExchangeApiDto> dtoList){
        ExchangeDateEntity saveExchangeDate = exchangeDateRepository.save(
                ExchangeDateEntity.builder()
                        .date(LocalDateTime.now())
                        .build()
        );

        for(ExchangeApiDto dto : dtoList){
            exchangeRepository.save(dto.toEntity(saveExchangeDate));
//            saveExchangeDate.getDataList().add(exchangeApiDataEntity);
        }
    }

    public ExchangeDto getRecentDataList(){
        Optional<ExchangeDateEntity> optionalExchangeDate = exchangeDateRepository.findByRecentData();
        return optionalExchangeDate.map(ExchangeDateEntity::toDto).orElse(null);
    }
}
