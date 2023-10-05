package com.example.urltracking.exceptionhandler;

import com.example.urltracking.api.common.response.CommonResponse;
import com.example.urltracking.enums.CodeEnum;
import com.example.urltracking.exception.CustomException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public CommonResponse handlerCustomException(CustomException e) {
        return CommonResponse.builder()
                .code(e.getCode())
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(RuntimeException.class)
    public CommonResponse handlerException(Exception e) {
        return CommonResponse.builder()
                .code(CodeEnum.UNKNOWN_ERROR.getCode())
                .message(e.getMessage())
                .build();
    }
}