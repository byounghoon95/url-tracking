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
public class UrlCreateServiceRequest {
    private String url;
    private String trackingUrl;

    @Builder
    public UrlCreateServiceRequest(String url,String trackingUrl) {
        this.url = url;
        this.trackingUrl = trackingUrl;
    }

    public void convertUrl() {
        String uuid = UUID.randomUUID().toString();
        this.trackingUrl = "https://make.my.url/" + uuid;
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
