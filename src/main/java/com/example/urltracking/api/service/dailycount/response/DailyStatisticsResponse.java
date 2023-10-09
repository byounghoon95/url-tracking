package com.example.urltracking.api.service.dailycount.response;

import com.example.urltracking.entity.dailycount.DailyCount;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@ApiModel(value = "7일 통계 조회수 응답 DTO", description = "요청 url을 필드로 갖는 DTO")
@Getter
public class DailyStatisticsResponse {

    @ApiModelProperty(value = "추적 가능한 Url")
    private String trackingUrl;

    @ApiModelProperty(value = "일별 조회수")
    private int count;

    @ApiModelProperty(value = "조회 일자")
    private LocalDate date;

    @Builder
    public DailyStatisticsResponse(String trackingUrl, int count, LocalDate date) {
        this.trackingUrl = trackingUrl;
        this.count = count;
        this.date = date;
    }

    public static DailyStatisticsResponse of(DailyCount dailyCount) {
        return DailyStatisticsResponse.builder()
                .trackingUrl(dailyCount.getTrackingUrl())
                .count(dailyCount.getDailyCount())
                .date(dailyCount.getDate())
                .build();
    }
}
