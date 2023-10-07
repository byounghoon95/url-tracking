package com.example.urltracking.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CodeEnum {

    SUCCESS("0000","SUCCESS"),
    URL_NOTFOUND("1111","VALID URL IS NOT EXIST"),
    UNKNOWN_ERROR("9999","UNKNOWN_ERROR"),
    ;

    private final String code;
    private final String message;
}
