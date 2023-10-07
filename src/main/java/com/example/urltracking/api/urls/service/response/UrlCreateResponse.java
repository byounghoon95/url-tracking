package com.example.urltracking.api.urls.service.response;

import com.example.urltracking.entity.urls.Urls;
import lombok.Builder;
import lombok.Getter;


@Getter
public class UrlCreateResponse {
    private String url;
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
