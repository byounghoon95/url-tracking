package com.example.urltracking.api.service.dailycount;

import com.example.urltracking.api.repository.dailycount.DailyCountRepository;
import com.example.urltracking.api.repository.urls.UrlsRepository;
import com.example.urltracking.api.service.dailycount.request.DailyStatisticsServiceRequest;
import com.example.urltracking.api.service.dailycount.response.DailyStatisticsResponse;
import com.example.urltracking.api.service.urls.UrlsService;
import com.example.urltracking.api.service.urls.request.UrlCreateServiceRequest;
import com.example.urltracking.api.service.urls.response.UrlCreateResponse;
import com.example.urltracking.entity.dailycount.DailyCount;
import com.example.urltracking.entity.urls.Urls;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@ActiveProfiles("test")
@SpringBootTest
class DailyCountServiceTest {
    @Autowired
    private DailyCountService dailyCountService;
    @Autowired
    private DailyCountRepository dailyCountRepository;
    @Autowired
    private UrlsRepository urlsRepository;

    private Urls createUrls(String url,String trackingUrl) {
        return Urls.builder()
                .url(url)
                .trackingUrl(trackingUrl)
                .totalCount(5)
                .build();
    }

    private DailyCount createDailyCount(String url, String trackingUrl, LocalDate date) {
        return DailyCount.builder()
                .url(url)
                .trackingUrl(trackingUrl)
                .date(date)
                .dailyCount(2)
                .build();
    }

    @BeforeEach
    private void setUp() {
        Urls url1 = createUrls("localhost://asdf/1","https://make.my.url/1");
        Urls url2 = createUrls("localhost://asdf/2","https://make.my.url/2");
        Urls url3 = createUrls("localhost://asdf/3","https://make.my.url/3");
        urlsRepository.saveAll(List.of(url1, url2, url3));

        DailyCount count1 = createDailyCount("localhost://asdf/1", "https://make.my.url/1", LocalDate.of(2023,10,7));
        DailyCount count2 = createDailyCount("localhost://asdf/1", "https://make.my.url/1",LocalDate.of(2023,10,8));
        DailyCount count3 = createDailyCount("localhost://asdf/2","https://make.my.url/2",LocalDate.of(2023,10,7));
        DailyCount count4 = createDailyCount("localhost://asdf/3","https://make.my.url/3",LocalDate.of(2023,10,7));
        dailyCountRepository.saveAll(List.of(count1, count2, count3, count4));
    }

    @DisplayName("7일간의 조회수 통계 데이터를 조회한다")
    @Test
    void get_daily_statistics() {
        //given
        DailyStatisticsServiceRequest request = DailyStatisticsServiceRequest.builder()
                .trackingUrl("https://make.my.url/1")
                .date(LocalDate.of(2023,10,8))
                .build();

        //when
        List<DailyStatisticsResponse> response = dailyCountService.getDailyStatistics(request);

        //then
        assertNotNull(response);
        assertThat(response.size()).isEqualTo(2);
        assertThat(response)
                .extracting("trackingUrl", "count", "date")
                .containsExactlyInAnyOrder(
                        tuple("https://make.my.url/1", 2, LocalDate.of(2023, 10, 7)),
                        tuple("https://make.my.url/1", 2, LocalDate.of(2023, 10, 8))
                        );
    }

}