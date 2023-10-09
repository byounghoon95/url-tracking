package com.example.urltracking.api.service.urls.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UrlCountServiceRequest {
    private String trackingUrl;

    @Builder
    public UrlCountServiceRequest(String trackingUrl) {
        this.trackingUrl = trackingUrl;
    }


}
