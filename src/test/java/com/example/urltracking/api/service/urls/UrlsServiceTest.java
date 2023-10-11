package com.example.urltracking.api.service.urls;

import com.example.urltracking.CommonServiceTest;
import com.example.urltracking.api.service.urls.request.UrlCountServiceRequest;
import com.example.urltracking.api.service.urls.request.UrlCreateServiceRequest;
import com.example.urltracking.api.service.urls.request.UrlUpdateServiceRequest;
import com.example.urltracking.api.service.urls.response.UrlCountResponse;
import com.example.urltracking.api.service.urls.response.UrlCreateResponse;
import com.example.urltracking.api.service.urls.response.UrlUpdateResponse;
import com.example.urltracking.entity.dailycount.DailyCount;
import com.example.urltracking.entity.urls.Urls;
import com.example.urltracking.exception.CustomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UrlsServiceTest extends CommonServiceTest {
    @DisplayName("새로운 url을 등록한다")
    @Test
    void create_url() {
        //given
        UrlCreateServiceRequest request = UrlCreateServiceRequest.builder()
                .url("localhost://test/10")
                .build();

        //when
        UrlCreateResponse response = urlsService.createUrl(request);

        //then
        assertNotNull(response);
        assertNotNull(response.getTrackingUrl());
        assertEquals(request.getUrl(), response.getUrl());
    }

    @DisplayName("url의 조회수를 증가시킨다")
    @Test
    void update_url_count() {
        //given
        UrlUpdateServiceRequest request = UrlUpdateServiceRequest.builder()
                .url("localhost://test/4")
                .trackingUrl("https://make.my.url/4")
                .dailyCount(5)
                .totalCount(5)
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
        UrlUpdateServiceRequest request = UrlUpdateServiceRequest.builder()
                .url("localhost://test/10")
                .trackingUrl("https://make.my.url/10")
                .dailyCount(0)
                .totalCount(0)
                .build();

        // when // then
        assertThatThrownBy(() -> urlsService.updateUrlCount(request))
                .isInstanceOf(CustomException.class)
                .hasMessage("VALID URL IS NOT EXIST");
    }

    @DisplayName("url의 조회수를 증가시킬 때 해당 날짜에 처음 클릭된 것이라면 데이터를 추가하고 수정한다")
    @Test
    void update_url_count_no_dailycount() {
        // given
        UrlUpdateServiceRequest request = UrlUpdateServiceRequest.builder()
                .url("localhost://test/3")
                .trackingUrl("https://make.my.url/3")
                .dailyCount(0)
                .totalCount(4)
                .build();

        //when
        UrlUpdateResponse response = urlsService.updateUrlCount(request);

        //then
        assertNotNull(response);
        assertThat(request.getUrl()).isEqualTo(response.getUrl());
        assertThat(request.getDailyCount() + 1).isEqualTo(response.getDailyCount());
        assertThat(request.getTotalCount() + 1).isEqualTo(response.getTotalCount());
    }

    @DisplayName("여러 쓰레드에서 주문 요청이 들어올 때, 조회수 증가 로직을 검증한다")
    @Test
    void update_url_count_with_concurrent_10_request() throws InterruptedException {
        //given
        UrlUpdateServiceRequest request = UrlUpdateServiceRequest.builder()
                .url("localhost://test/3")
                .trackingUrl("https://make.my.url/3")
                .dailyCount(0)
                .totalCount(4)
                .build();

        //when
        int threadCount = 10;
        ExecutorService executorService2 = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch2 = new CountDownLatch (threadCount);

        for (int i = 0; i < 10; i++) {
            executorService2.execute(() -> {
                urlsService.updateUrlCount(request);
                latch2.countDown();
            });
        }

        latch2.await();

        //then
        Urls urlResponse = urlsRepository.findByTrackingUrl(request.getTrackingUrl()).get();
        DailyCount dailyCountResponse = dailyCountRepository.findByTrackingUrlWithDate(request.getTrackingUrl()).get();

        assertThat(dailyCountResponse.getDailyCount()).isEqualTo(10);
        assertThat(urlResponse.getTotalCount()).isEqualTo(14);
    }


    @DisplayName("url로 오늘/누적 조회수를 조회한다")
    @Test
    void get_url_count() {
        // given
        UrlCountServiceRequest request = UrlCountServiceRequest.builder()
                .trackingUrl("https://make.my.url/2")
                .build();

        // when
        UrlCountResponse response = urlsService.getUrlCount(request);

        // then
        assertNotNull(response);
        assertThat(response.getDailyCount()).isEqualTo(0);
        assertThat(response.getTotalCount()).isEqualTo(3);
    }
}