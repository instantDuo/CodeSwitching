package com.instantduo.codeswitching.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseMessage<T> {
    private String msg;
    private int statusCode;
    private T data;

    public ResponseMessage(String msg, int statusCode){
        this.msg = msg;
        this.statusCode = statusCode;
    }

    public ResponseMessage(String msg, int statusCode, T data) {
        this.msg = msg;
        this.statusCode = statusCode;
        this.data = data;
    }
}
