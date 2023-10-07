package com.example.urltracking.api.urls.controller.request;

import com.example.urltracking.api.urls.service.request.UrlCreateServiceRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UrlCreateRequest {

    private String url;

    @Builder
    public UrlCreateRequest(String url) {
        this.url = url;
    }

    public UrlCreateServiceRequest toServiceRequest() {
        return UrlCreateServiceRequest.builder()
                .url(url)
                .build();
    }

}
