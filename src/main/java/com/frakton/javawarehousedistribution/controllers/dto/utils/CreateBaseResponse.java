package com.frakton.javawarehousedistribution.controllers.dto.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CreateBaseResponse {
    public ResponseEntity<BaseResponse> createResponse(String message, HttpStatus status,Object data){
        return ResponseEntity.status(status).body(new BaseResponse(message,status,data));
    }
    public ResponseEntity<BaseResponse> createBadResponse(String message, HttpStatus status){
        return ResponseEntity.status(status).body(new BaseResponse(message,status,null));
    }
}
