package com.example.urltracking.api.controller.urls;

import com.example.urltracking.api.common.response.CommonResponse;
import com.example.urltracking.api.controller.urls.request.UrlCreateRequest;
import com.example.urltracking.api.service.urls.UrlsService;
import com.example.urltracking.api.service.urls.response.UrlCreateResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("Urls Controller API")
@RequestMapping("/api/urls")
@RequiredArgsConstructor
@RestController
public class UrlsApiController {
    private final UrlsService urlsService;

    @ApiOperation(value = "url 생성", notes = "url을 받아 추적 가능한 url을 추가해 반환한다")
    @PostMapping("/create")
    public CommonResponse<UrlCreateResponse> createUrl(@RequestBody UrlCreateRequest request) {
        return new CommonResponse<>(urlsService.createUrl(request.toServiceRequest()));
    }
}
