package com.pjay.exchange_server.model.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExchangeListDto {
    private Long idx;
    private String amount; // 매매기준율
    private String unit;
    private String curName;
}
