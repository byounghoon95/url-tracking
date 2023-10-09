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

    @BeforeEach
    protected void setUp() {
        Urls url1 = createUrls("localhost://test/1","https://make.my.url/1",5);
        Urls url2 = createUrls("localhost://test/2","https://make.my.url/2",3);
        Urls url3 = createUrls("localhost://test/3","https://make.my.url/3",4);
        Urls url4 = createUrls("localhost://test/4","https://make.my.url/4",5);
        urlsRepository.saveAll(List.of(url1, url2, url3, url4));

        DailyCount count1 = createDailyCount("localhost://test/1", "https://make.my.url/1", LocalDate.of(2023,10,1),3);
        DailyCount count2 = createDailyCount("localhost://test/1", "https://make.my.url/1", LocalDate.of(2023,10,8),3);
        DailyCount count3 = createDailyCount("localhost://test/1", "https://make.my.url/1",LocalDate.of(2023,10,9),2);
        DailyCount count4 = createDailyCount("localhost://test/2","https://make.my.url/2",LocalDate.of(2023,10,7),3);
        DailyCount count5 = createDailyCount("localhost://test/3","https://make.my.url/3",LocalDate.of(2023,10,7),4);
        DailyCount count6 = createDailyCount("localhost://test/4","https://make.my.url/4",LocalDate.now(),5);
        dailyCountRepository.saveAll(List.of(count1, count2, count3, count4, count5, count6));
    }
}
