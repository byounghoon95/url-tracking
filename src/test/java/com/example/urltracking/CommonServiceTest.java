package com.example.urltracking;

import com.example.urltracking.api.repository.dailycount.DailyCountRepository;
import com.example.urltracking.api.repository.urls.UrlsRepository;
import com.example.urltracking.api.service.dailycount.DailyCountService;
import com.example.urltracking.api.service.urls.UrlsService;
import com.example.urltracking.entity.dailycount.DailyCount;
import com.example.urltracking.entity.urls.Urls;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional
@ActiveProfiles("test")
@SpringBootTest
public abstract class CommonServiceTest {
    @Autowired
    protected UrlsService urlsService;
    @Autowired
    protected UrlsRepository urlsRepository;
    @Autowired
    protected DailyCountService dailyCountService;
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
