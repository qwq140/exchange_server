package com.pjay.exchange_server.model.entity;

import com.pjay.exchange_server.model.dto.response.ExchangeListDto;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_exchange_data")
public class ExchangeApiDataEntity {

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String amount; // 매매기준율
    private String unit;
    private String curName;

    @ManyToOne
    @JoinColumn(name = "data_master_idx")
    private ExchangeDateEntity master;

    @CreationTimestamp
    private LocalDateTime createdDate;

    public ExchangeListDto toDto(){
        return ExchangeListDto.builder()
                .idx(idx)
                .amount(amount)
                .unit(unit)
                .curName(curName)
                .build();
    }

}
