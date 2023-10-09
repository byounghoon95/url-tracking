package com.example.urltracking.api.repository.dailycount;

import com.example.urltracking.CommonRepositoryTest;
import com.example.urltracking.entity.dailycount.DailyCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@ActiveProfiles("test")
@DataJpaTest
class DailyCountRepositoryTest extends CommonRepositoryTest {

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
        assertThat(response.getDailyCount()).isEqualTo(5);
    }

    @DisplayName("트래킹 url과 이라로 7일간의 조회수를 조회한다")
    @Test
    void find_all_by_url_and_date() {
        // given
        String trackingUrl = "https://make.my.url/1";
        LocalDate startDate = LocalDate.of(2023, 10, 9);
        LocalDate endDate = startDate.minusDays(7);

        // when
        List<DailyCount> response = dailyCountRepository.findAllByUrlAndDate(trackingUrl, startDate, endDate);

        // then
        assertThat(response.size()).isEqualTo(2);
        assertThat(response)
                .extracting("trackingUrl", "dailyCount", "date")
                .containsExactlyInAnyOrder(
                        tuple("https://make.my.url/1", 3, LocalDate.of(2023, 10, 8)),
                        tuple("https://make.my.url/1", 2, LocalDate.of(2023, 10, 9))
                );
    }
}