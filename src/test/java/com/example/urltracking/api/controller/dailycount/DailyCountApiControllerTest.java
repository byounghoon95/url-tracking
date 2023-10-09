package com.example.urltracking.api.controller.dailycount;

import com.example.urltracking.CommonControllerTest;
import com.example.urltracking.api.controller.dailycount.request.DailyStatisticsRequest;
import com.example.urltracking.enums.CodeEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ActiveProfiles("test")
@WebMvcTest
class DailyCountApiControllerTest extends CommonControllerTest {

    @DisplayName("7일간의 조회수 통계 데이터를 조회한다")
    @Test
    void get_daily_statistics() throws Exception {
        //given
        DailyStatisticsRequest request = dailyStatisticsRequest();

        //when //then
        mockMvc.perform(get("/api/dailycount/daily/statistic")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(jsonPath("$.code").value(CodeEnum.SUCCESS.getCode()))
        ;
    }

}