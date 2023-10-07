package com.example.urltracking.api.service.urls.request;

import com.example.urltracking.entity.dailycount.DailyCount;
import com.example.urltracking.entity.urls.Urls;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class UrlUpdateServiceRequest {
    private String url;
    private String trackingUrl;
    private int dailyCount;
    private int totalCount;

    @Builder
    public UrlUpdateServiceRequest(String url, String trackingUrl, int dailyCount, int totalCount) {
        this.url = url;
        this.trackingUrl = trackingUrl;
        this.dailyCount = dailyCount;
        this.totalCount = totalCount;
    }

    public Urls toUrlsEntity() {
        return Urls.builder()
                .url(url)
                .trackingUrl(trackingUrl)
                .build();
    }

    public DailyCount toDailyCountEntity() {
        return DailyCount.builder()
                .url(url)
                .trackingUrl(trackingUrl)
                .date(LocalDate.now())
                .dailyCount(0)
                .build();
    }
}

