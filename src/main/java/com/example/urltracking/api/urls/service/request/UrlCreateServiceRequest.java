package com.example.urltracking.api.urls.service.request;

import com.example.urltracking.entity.urls.Urls;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public Urls toEntity() {
        return Urls.builder()
                .url(url)
                .trackingUrl(trackingUrl)
                .build();
    }
}
