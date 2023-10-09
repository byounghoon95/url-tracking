package com.example.urltracking.api.repository.urls;

import com.example.urltracking.CommonRepositoryTest;
import com.example.urltracking.entity.urls.Urls;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DataJpaTest
class UrlsRepositoryTest extends CommonRepositoryTest {
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
        String trackingUrl = "https://make.my.url/10";

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
        assertThat(response.getUrl()).isEqualTo("localhost://test/1");
        assertThat(response.getTrackingUrl()).isEqualTo("https://make.my.url/1");
    }

    @DisplayName("트래킹 url로 오늘/누적 카운드를 반환한다")
    @Test
    void find_count_by_tracking_url() {
        // given
        String trackingUrl = "https://make.my.url/4";

        // when
        Urls response = urlsRepository.findCountByTrackingUrl(trackingUrl).get();

        // then
        assertThat(response.getUrl()).isEqualTo("localhost://test/4");
        assertThat(response.getTrackingUrl()).isEqualTo("https://make.my.url/4");
        assertThat(response.getDailyCount()).isEqualTo(5);
        assertThat(response.getTotalCount()).isEqualTo(5);
    }
}