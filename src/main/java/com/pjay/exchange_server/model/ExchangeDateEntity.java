package com.pjay.exchange_server.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

}
