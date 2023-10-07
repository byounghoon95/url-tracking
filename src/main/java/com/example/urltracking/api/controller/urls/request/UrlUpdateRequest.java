package com.example.urltracking.api.controller.urls.request;

import com.example.urltracking.api.service.urls.request.UrlUpdateServiceRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "Url 조회수 수정 DTO", description = "추적 가능한 trackingUrl을 필드로 갖는 DTO")
@Getter
@NoArgsConstructor
public class UrlUpdateRequest {

    @ApiModelProperty(value = "요청 Url")
    @NotBlank(message = "url은 필수입니다")
    private String trackingUrl;

    @Builder
    public UrlUpdateRequest(String trackingUrl) {
        this.trackingUrl = trackingUrl;
    }

    public UrlUpdateServiceRequest toServiceRequest() {
        return UrlUpdateServiceRequest.builder()
                .trackingUrl(trackingUrl)
                .build();
    }
}
