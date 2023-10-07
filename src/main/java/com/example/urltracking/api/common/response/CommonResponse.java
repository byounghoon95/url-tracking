package com.example.urltracking.api.common.response;

import com.example.urltracking.enums.CodeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.aspectj.apache.bcel.classfile.Code;

@ApiModel(value = "공통 응답 DTO")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse<T> {
    @ApiModelProperty(value = "응답 코드")
    private String code;
    @ApiModelProperty(value = "응답 메세지")
    private String message;
    @ApiModelProperty(value = "응답 정보")
    private T info;

    public CommonResponse(CodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.message = codeEnum.getMessage();
    }

    public CommonResponse(T info) {
        this.code = CodeEnum.SUCCESS.getCode();
        this.message = CodeEnum.SUCCESS.getMessage();
        this.info = info;
    }

    public CommonResponse(CodeEnum codeEnum, T info) {
        this.code = codeEnum.getCode();
        this.message = codeEnum.getMessage();
        this.info = info;
    }
}
