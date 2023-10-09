package com.example.urltracking.api.controller.urls.request;

import com.example.urltracking.api.service.urls.request.UrlCountServiceRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "조회수 요청 DTO", description = "요청 url을 필드로 갖는 DTO")
@Getter
@NoArgsConstructor
public class UrlCountRequest {

    @ApiModelProperty(value = "요청 Url")
    @NotBlank(message = "url은 필수입니다")
    private String trackingUrl;

    @Builder
    public UrlCountRequest(String trackingUrl) {
        this.trackingUrl = trackingUrl;
    }

    public UrlCountServiceRequest toServiceRequest() {
        return UrlCountServiceRequest.builder()
                .trackingUrl(trackingUrl)
                .build();
    }
}
