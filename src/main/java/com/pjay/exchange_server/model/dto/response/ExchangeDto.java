package com.pjay.exchange_server.model.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ExchangeDto {

    private Long idx;
    private LocalDateTime date;
    private List<ExchangeListDto> list;

}
