package com.example.urltracking.api.service.dailycount.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class DailyStatisticsServiceRequest {

    private String trackingUrl;
    private LocalDate date;

    @Builder
    public DailyStatisticsServiceRequest(String trackingUrl, LocalDate date) {
        this.trackingUrl = trackingUrl;
        this.date = date;
    }
}
