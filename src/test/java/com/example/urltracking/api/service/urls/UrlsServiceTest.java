package com.example.urltracking.api.service.urls;

import com.example.urltracking.api.repository.urls.UrlsRepository;
import com.example.urltracking.api.service.urls.request.UrlCreateServiceRequest;
import com.example.urltracking.api.service.urls.request.UrlUpdateServiceRequest;
import com.example.urltracking.api.service.urls.response.UrlCreateResponse;
import com.example.urltracking.api.service.urls.response.UrlUpdateResponse;
import com.example.urltracking.entity.urls.Urls;
import com.example.urltracking.exception.CustomException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@ActiveProfiles("test")
@SpringBootTest
class UrlsServiceTest {
    @Autowired
    private UrlsService urlsService;
    @Autowired
    private UrlsRepository urlsRepository;

    private Urls createUrl(String url, String trackingUrl) {
        return Urls.builder()
                .url(url)
                .trackingUrl(trackingUrl)
                .totalCount(0)
                .build();
    }

    @DisplayName("새로운 url을 등록한다")
    @Test
    void create_url() {
        //given
        UrlCreateServiceRequest request = UrlCreateServiceRequest.builder()
                .url("www.abc.com/1/2")
                .build();

        //when
        UrlCreateResponse response = urlsService.createUrl(request);

        //then
        assertNotNull(response);
        assertNotNull(response.getTrackingUrl());
        assertEquals(request.getUrl(), response.getUrl());
    }

    private void setUpUrl() {
        Urls url1 = createUrl("www.abc.com/1/2", "www.abc.com/asdffxcvvdffaf");
        urlsRepository.saveAll(List.of(url1));
    }

    @DisplayName("url의 조회수를 증가시킨다")
    @Test
    void update_url_count() {
        //given
        setUpUrl();
        UrlUpdateServiceRequest request = UrlUpdateServiceRequest.builder()
                .url("www.abc.com/1/2")
                .trackingUrl("www.abc.com/asdffxcvvdffaf")
                .dailyCount(0)
                .totalCount(0)
                .build();

        //when
        UrlUpdateResponse response = urlsService.updateUrlCount(request);

        //then
        assertNotNull(response);
        assertEquals(request.getUrl(), response.getUrl());
        assertThat(request.getDailyCount() + 1).isEqualTo(response.getDailyCount());
        assertThat(request.getTotalCount() + 1).isEqualTo(response.getTotalCount());
    }

    @DisplayName("url의 조회수를 증가시킬 때 url이 등록되어있지 않으면 예외가 발생한다")
    @Test
    void update_url_count_with_no_url() {
        // given
        setUpUrl();
        UrlUpdateServiceRequest request = UrlUpdateServiceRequest.builder()
                .url("www.abc.com/1/2")
                .trackingUrl("www.abc.com/123123")
                .dailyCount(0)
                .totalCount(0)
                .build();

        // when // then
        assertThatThrownBy(() -> urlsService.updateUrlCount(request))
                .isInstanceOf(CustomException.class)
                .hasMessage("VALID URL IS NOT EXIST");
    }
}