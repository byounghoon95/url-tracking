package com.example.urltracking.api.service.urls.response;

import com.example.urltracking.entity.urls.Urls;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@ApiModel(value = "Url 조회수 수정 DTO", description = "url, trackingUrl, dailyCount, totalCount를 필드로 갖는 DTO")
@Getter
public class UrlUpdateResponse {
    @ApiModelProperty(value = "기본 Url")
    private String url;

    @ApiModelProperty(value = "추적 가능한 Url")
    private String trackingUrl;

    @ApiModelProperty(value = "오늘 조회수")
    private int dailyCount;

    @ApiModelProperty(value = "총 조회수")
    private int totalCount;

    @Builder
    public UrlUpdateResponse(String url, String trackingUrl, int dailyCount, int totalCount) {
        this.url = url;
        this.trackingUrl = trackingUrl;
        this.dailyCount = dailyCount;
        this.totalCount = totalCount;
    }

    public static UrlUpdateResponse of(Urls url) {
        return UrlUpdateResponse.builder()
                .url(url.getUrl())
                .trackingUrl(url.getTrackingUrl())
                .dailyCount(url.getDailyCount())
                .totalCount(url.getCount())
                .build();
    }
}
