package com.example.urltracking.api.controller.urls.request;

import com.example.urltracking.api.service.urls.request.UrlCountServiceRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@ApiModel(value = "조회수 요청 DTO", description = "요청 url을 필드로 갖는 DTO")
@Getter
@NoArgsConstructor
public class UrlCountRequest {

    @ApiModelProperty(value = "요청 Url")
    @NotBlank(message = "url은 필수입니다")
    private String trackingUrl;

    @ApiModelProperty(value = "조회 일자")
    @NotNull(message = "date는 필수입니다")
    private LocalDate date;

    @Builder
    public UrlCountRequest(String trackingUrl, LocalDate date) {
        this.trackingUrl = trackingUrl;
        this.date = date;
    }

    public UrlCountServiceRequest toServiceRequest() {
        return UrlCountServiceRequest.builder()
                .trackingUrl(trackingUrl)
                .date(date)
                .build();
    }
}