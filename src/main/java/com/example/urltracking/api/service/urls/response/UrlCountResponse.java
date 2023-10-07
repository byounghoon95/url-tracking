package com.example.urltracking.api.service.urls.response;

import com.example.urltracking.entity.urls.Urls;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@ApiModel(value = "조회수 응답 DTO", description = "요청 url, 조회 일자, 일간/누적 조회수를 필드로 갖는 DTO")
@Getter
public class UrlCountResponse {

    @ApiModelProperty(value = "응답 Url")
    private String url;

    @ApiModelProperty(value = "추적 가능한 Url")
    private String trackingUrl;

    @ApiModelProperty(value = "조회 일자")
    private LocalDate date;

    @ApiModelProperty(value = "일간 조회수")
    private int dailyCount;

    @ApiModelProperty(value = "누적 조회수")
    private int totalCount;

    @Builder
    public UrlCountResponse(String url, String trackingUrl, LocalDate date, int dailyCount, int totalCount) {
        this.url = url;
        this.trackingUrl = trackingUrl;
        this.date = date;
        this.dailyCount = dailyCount;
        this.totalCount = totalCount;
    }

    public static UrlCountResponse of(Urls url) {
        return UrlCountResponse.builder()
                .url(url.getUrl())
                .trackingUrl(url.getTrackingUrl())
                .dailyCount(url.getDailyCount())
                .totalCount(url.getTotalCount())
                .date(url.getDate())
                .build();
    }
}
