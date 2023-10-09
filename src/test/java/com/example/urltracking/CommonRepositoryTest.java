package com.example.urltracking;

import com.example.urltracking.api.repository.dailycount.DailyCountRepository;
import com.example.urltracking.api.repository.urls.UrlsRepository;
import com.example.urltracking.entity.dailycount.DailyCount;
import com.example.urltracking.entity.urls.Urls;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

@ActiveProfiles("test")
@DataJpaTest
public abstract class CommonRepositoryTest {

    @Autowired
    protected UrlsRepository urlsRepository;
    @Autowired
    protected DailyCountRepository dailyCountRepository;

    protected Urls createUrls(String url, String trackingUrl, int count) {
        return Urls.builder()
                .url(url)
                .trackingUrl(trackingUrl)
                .totalCount(count)
                .build();
    }

    protected DailyCount createDailyCount(String url, String trackingUrl, LocalDate date, int count) {
        return DailyCount.builder()
                .url(url)
                .trackingUrl(trackingUrl)
                .date(date)
                .dailyCount(count)
                .build();
    }
}
