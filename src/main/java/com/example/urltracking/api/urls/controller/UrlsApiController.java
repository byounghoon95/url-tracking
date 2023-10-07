package com.example.urltracking.api.urls.controller;

import com.example.urltracking.api.common.response.CommonResponse;
import com.example.urltracking.api.urls.controller.request.UrlCreateRequest;
import com.example.urltracking.api.urls.service.UrlsService;
import com.example.urltracking.api.urls.service.response.UrlCreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/urls")
@RequiredArgsConstructor
@RestController
public class UrlsApiController {
    private final UrlsService urlsService;

    @PostMapping("/create")
    public CommonResponse<UrlCreateResponse> createUrl(@RequestBody UrlCreateRequest request) {
        return new CommonResponse<>(urlsService.createUrl(request.toServiceRequest()));
    }
}
