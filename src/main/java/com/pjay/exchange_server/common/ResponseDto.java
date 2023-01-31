package com.pjay.exchange_server.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class ResponseDto<T> {

    private final Integer code;
    private final String message;
    private final T content;

    public ResponseDto(int code, String message) {
        this.code = code;
        this.message = message;
        this.content = null;
    }
}