package com.pjay.exchange_server.controller;

import com.pjay.exchange_server.common.ResponseDto;
import com.pjay.exchange_server.model.dto.response.ExchangeApiDto;
import com.pjay.exchange_server.model.dto.response.ExchangeDto;
import com.pjay.exchange_server.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ExchangeApiController {

    private final String apiUrl = "https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?data=AP01";
    private final String apiKey = "API KEY";

    private final RestTemplate restTemplate;
    private final ExchangeService exchangeService;

    @GetMapping("/api/exchange/data")
    public HttpEntity<?> api(){
        String realUrl = apiUrl + "&authkey="+apiKey;
        System.out.println(realUrl);
        ResponseEntity<ExchangeApiDto[]> response = restTemplate.getForEntity(realUrl, ExchangeApiDto[].class);

        ExchangeApiDto[] resultData = response.getBody();
        List<ExchangeApiDto> dtoList = Arrays.asList(resultData);
        System.out.println(dtoList.size());
        exchangeService.saveAll(dtoList);

        return new ResponseEntity<>(new ResponseDto<>(1, "성공"), HttpStatus.OK);
    }


    @Scheduled(cron = "0 0 12 * * *")
    public void scheduleApi(){
        String realUrl = apiUrl + "&authkey="+apiKey;
        System.out.println(realUrl);
        ResponseEntity<ExchangeApiDto[]> response = restTemplate.getForEntity(realUrl, ExchangeApiDto[].class);

        ExchangeApiDto[] resultData = response.getBody();
        List<ExchangeApiDto> dtoList = Arrays.asList(resultData);

        if(dtoList.get(0).getResult() == 1){
            exchangeService.saveAll(dtoList);
        }
    }

    @GetMapping("/api/exchange/recent")
    public HttpEntity<?> getRecentExchange(){
        ExchangeDto dto = exchangeService.getRecentDataList();
        if(dto == null){
            return new ResponseEntity<>(new ResponseDto<>(-1, "데이터가 없습니다."), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDto<>(1, "최근 환율 데이터 조회에 성공하였습니다.", dto), HttpStatus.OK);
    }
}
