package com.pjay.exchange_server.controller;

import com.pjay.exchange_server.common.ResponseDto;
import com.pjay.exchange_server.model.ExchangeDto;
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
import java.util.Date;
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
        ResponseEntity<ExchangeDto[]> response = restTemplate.getForEntity(realUrl, ExchangeDto[].class);

        ExchangeDto[] resultData = response.getBody();
        List<ExchangeDto> dtoList = Arrays.asList(resultData);
        System.out.println(dtoList.size());
        exchangeService.saveAll(dtoList);

        return new ResponseEntity<>(new ResponseDto<>(1, "성공"), HttpStatus.OK);
    }


    @Scheduled(cron = "0 0 12 * * *")
    public void scheduleApi(){
        String realUrl = apiUrl + "&authkey="+apiKey;
        System.out.println(realUrl);
        ResponseEntity<ExchangeDto[]> response = restTemplate.getForEntity(realUrl, ExchangeDto[].class);

        ExchangeDto[] resultData = response.getBody();
        List<ExchangeDto> dtoList = Arrays.asList(resultData);

        if(dtoList.get(0).getResult() == 1){
            exchangeService.saveAll(dtoList);
        }
    }

    @Scheduled(cron = "10 * * * * *")
    public void test () {
        System.out.println(1);
    }
}
