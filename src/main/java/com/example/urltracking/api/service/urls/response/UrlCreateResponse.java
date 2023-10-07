package com.example.urltracking.api.service.urls.response;

import com.example.urltracking.entity.urls.Urls;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@ApiModel(value = "Url 생성 응답 DTO", description = "요청 url(url)과 추적 가능한 url(trackingUrl)을 필드로 갖는 DTO")
@Getter
public class UrlCreateResponse {

    @ApiModelProperty(value = "요청 Url")
    private String url;
    @ApiModelProperty(value = "추적 가능한 Url")
    private String trackingUrl;

    @Builder
    public UrlCreateResponse(String url,String trackingUrl) {
        this.url = url;
        this.trackingUrl = trackingUrl;
    }

    public static UrlCreateResponse of(Urls url) {
        return UrlCreateResponse.builder()
                .url(url.getUrl())
                .trackingUrl(url.getTrackingUrl())
                .build();
    }
}
