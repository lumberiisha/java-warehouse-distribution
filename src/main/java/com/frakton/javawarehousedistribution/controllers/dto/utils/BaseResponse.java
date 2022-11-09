package com.frakton.javawarehousedistribution.controllers.dto.utils;

import org.springframework.http.HttpStatus;

public class BaseResponse {
    private String message;
    private HttpStatus code;
    private Object data;

    public BaseResponse(String message, HttpStatus code, Object data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = HttpStatus.valueOf(code);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
