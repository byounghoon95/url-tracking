package com.example.urltracking.api.service.urls.request;

import com.example.urltracking.entity.dailycount.DailyCount;
import com.example.urltracking.entity.urls.Urls;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class UrlCountServiceRequest {
    private String trackingUrl;
    private LocalDate date;


    @Builder
    public UrlCountServiceRequest(String trackingUrl, LocalDate date) {
        this.trackingUrl = trackingUrl;
        this.date = date;
    }
}
