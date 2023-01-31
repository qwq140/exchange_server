package com.pjay.exchange_server.model.dto.response;

import com.pjay.exchange_server.model.entity.ExchangeApiDataEntity;
import com.pjay.exchange_server.model.entity.ExchangeDateEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeApiDto {
    /**
     * "result":1, 1: 조회 성공, 2: DATA 코드 오류 3: 인증코드 오, 4: 일일 제한횟수 마감
     * "cur_unit":"USD", 통화코드
     * "ttb":"1,216.41", 전신환 받을때
     * "tts":"1,240.98", 전신환 보낼때
     * "deal_bas_r":"1,228.7", 매매기준율
     * "bkpr":"1,228", 장부가격
     * "yy_efee_r":"0", 년환가로율
     * "ten_dd_efee_r":"0", 10일환가로율
     * "kftc_bkpr":"1,228", 서울외국환중개 매매기준율
     * "kftc_deal_bas_r":"1,228.7", 서울외국한중개 장부가격
     * "cur_nm":"미국 달러"
     */
    private int result;
    private String cur_unit;
    private String ttb;
    private String tts;
    private String deal_bas_r;
    private String bkpr;
    private String yy_efee_r;
    private String ten_dd_efee_r;
    private String kftc_bkpr;
    private String kftc_deal_bas_r;
    private String cur_nm;

    public ExchangeApiDataEntity toEntity(ExchangeDateEntity exchangeDate){
        return ExchangeApiDataEntity.builder()
                .amount(deal_bas_r)
                .unit(cur_unit)
                .curName(cur_nm)
                .master(exchangeDate)
                .build();
    }
}
