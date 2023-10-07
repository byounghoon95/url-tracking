package com.example.urltracking.api.repository.urls;

import com.example.urltracking.entity.urls.Urls;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DataJpaTest
class UrlsRepositoryTest {

    @Autowired
    UrlsRepository urlsRepository;

    private Urls createUrls(String url,String trackingUrl) {
        return Urls.builder()
                .url(url)
                .trackingUrl(trackingUrl)
                .count(0)
                .build();
    }

    @BeforeEach
    private void setUp() {
        Urls url1 = createUrls("localhost://asdf/1","https://make.my.url/1");
        Urls url2 = createUrls("localhost://asdf/2","https://make.my.url/2");
        Urls url3 = createUrls("localhost://asdf/3","https://make.my.url/3");
        urlsRepository.saveAll(List.of(url1, url2, url3));
    }

    @DisplayName("트래킹 url이 중복되는지 확인하고 중복되면 true를 반환한다")
    @Test
    void exists_by_traking_url_return_true() {
        // given
        String trackingUrl = "https://make.my.url/1";

        // when
        boolean response = urlsRepository.existsByTrackingUrl(trackingUrl);

        // then
        assertThat(response).isEqualTo(true);
    }

    @DisplayName("트래킹 url이 중복되는지 확인하고 중복되면 false를 반환한다")
    @Test
    void exists_by_traking_url_return_false() {
        // given
        String trackingUrl = "https://make.my.url/4";

        // when
        boolean response = urlsRepository.existsByTrackingUrl(trackingUrl);

        // then
        assertThat(response).isEqualTo(false);
    }

    @DisplayName("트래킹 url을 조회한다")
    @Test
    void find_by_tracking_url() {
        // given
        String trackingUrl = "https://make.my.url/1";

        // when
        Urls response = urlsRepository.findByTrackingUrl(trackingUrl).get();

        // then
        assertThat(response.getUrl()).isEqualTo("localhost://asdf/1");
        assertThat(response.getTrackingUrl()).isEqualTo("https://make.my.url/1");
    }
}