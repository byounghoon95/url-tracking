package com.example.urltracking.api.service.dailycount;

import com.example.urltracking.CommonServiceTest;
import com.example.urltracking.api.service.dailycount.request.DailyStatisticsServiceRequest;
import com.example.urltracking.api.service.dailycount.response.DailyStatisticsResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DailyCountServiceTest extends CommonServiceTest {

    @DisplayName("7일간의 조회수 통계 데이터를 조회한다")
    @Test
    void get_daily_statistics() {
        //given
        DailyStatisticsServiceRequest request = DailyStatisticsServiceRequest.builder()
                .trackingUrl("https://make.my.url/1")
                .date(LocalDate.of(2023,10,9))
                .build();

        //when
        List<DailyStatisticsResponse> response = dailyCountService.getDailyStatistics(request);

        //then
        assertNotNull(response);
        assertThat(response.size()).isEqualTo(2);
        assertThat(response)
                .extracting("trackingUrl", "count", "date")
                .containsExactlyInAnyOrder(
                        tuple("https://make.my.url/1", 3, LocalDate.of(2023, 10, 2)),
                        tuple("https://make.my.url/1", 2, LocalDate.of(2023, 10, 3))
                        );
    }

}