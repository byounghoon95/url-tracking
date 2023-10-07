package com.example.urltracking.api.controller.urls.request;

import com.example.urltracking.api.service.urls.request.UrlCreateServiceRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@ApiModel(value = "Url 생성 DTO", description = "요청 url(url)을 필드로 갖는 DTO")
@Getter
@NoArgsConstructor
public class UrlCreateRequest {

    @ApiModelProperty(value = "요청 Url")
    private String url;

    @Builder
    public UrlCreateRequest(String url) {
        this.url = url;
    }

    public UrlCreateServiceRequest toServiceRequest() {
        return UrlCreateServiceRequest.builder()
                .url(url)
                .build();
    }

}
