package com.example.urltracking.api.repository.dailycount;

import com.example.urltracking.api.repository.urls.UrlsRepository;
import com.example.urltracking.entity.dailycount.DailyCount;
import com.example.urltracking.entity.urls.Urls;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
class DailyCountRepositoryTest {
    @Autowired
    UrlsRepository urlsRepository;
    @Autowired
    DailyCountRepository dailyCountRepository;

    private Urls createUrls(String url, String trackingUrl) {
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

        DailyCount count1 = createDailyCount("localhost://asdf/1", "https://make.my.url/1",LocalDate.of(2023,10,7));
        DailyCount count2 = createDailyCount("localhost://asdf/1", "https://make.my.url/1",LocalDate.of(2023,10,8));
        DailyCount count3 = createDailyCount("localhost://asdf/2","https://make.my.url/2",LocalDate.of(2023,10,7));
        DailyCount count4 = createDailyCount("localhost://asdf/3","https://make.my.url/3",LocalDate.of(2023,10,7));
        DailyCount count5 = createDailyCount("localhost://asdf/4","https://make.my.url/4",LocalDate.now());
        dailyCountRepository.saveAll(List.of(count1, count2, count3, count4,count5));
    }

    @DisplayName("트래킹 url로 일간 조회수를 조회한다")
    @Test
    void find_by_tracking_url_with_date() {
        // given
        String trackingUrl = "https://make.my.url/4";

        // when
        DailyCount response = dailyCountRepository.findByTrackingUrlWithDate(trackingUrl).get();

        // then
        assertThat(response).isNotNull();
        assertThat(response.getTrackingUrl()).isEqualTo(trackingUrl);
        assertThat(response.getDailyCount()).isEqualTo(2);
    }

    @DisplayName("트래킹 url과 이라로 7일간의 조회수를 조회한다")
    @Test
    void find_all_by_url_and_date() {
        // given
        String trackingUrl = "https://make.my.url/1";
        LocalDate startDate = LocalDate.of(2023, 10, 8);
        LocalDate endDate = startDate.minusDays(7);

        // when
        List<DailyCount> response = dailyCountRepository.findAllByUrlAndDate(trackingUrl, startDate, endDate);

        // then
        assertThat(response.size()).isEqualTo(2);
        assertThat(response)
                .extracting("trackingUrl", "dailyCount", "date")
                .containsExactlyInAnyOrder(
                        tuple("https://make.my.url/1", 2, LocalDate.of(2023, 10, 7)),
                        tuple("https://make.my.url/1", 2, LocalDate.of(2023, 10, 8))
                );
    }
}