package com.pjay.exchange_server.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pjay.exchange_server.model.dto.response.ExchangeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_exchange_date")
public class ExchangeDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private LocalDateTime date;

    @JsonIgnoreProperties({"master"})
    @OneToMany(fetch = FetchType.EAGER,  mappedBy = "master")
    private List<ExchangeApiDataEntity> dataList;


    public ExchangeDto toDto(){
        return ExchangeDto.builder()
                .idx(idx)
                .date(date)
                .list(dataList.stream().map(ExchangeApiDataEntity::toDto).collect(Collectors.toList()))
                .build();
    }
}
