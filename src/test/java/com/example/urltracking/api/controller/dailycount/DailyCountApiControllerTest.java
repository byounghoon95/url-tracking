package com.example.urltracking.api.controller.dailycount;

import com.example.urltracking.api.controller.dailycount.request.DailyStatisticsRequest;
import com.example.urltracking.api.controller.urls.request.UrlCreateRequest;
import com.example.urltracking.api.service.dailycount.DailyCountService;
import com.example.urltracking.api.service.urls.UrlsService;
import com.example.urltracking.enums.CodeEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ActiveProfiles("test")
@WebMvcTest
class DailyCountApiControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DailyCountService dailyCountService;
    @MockBean
    private UrlsService urlsService;
    
    private DailyStatisticsRequest dailyStatisticsRequest() {
        return DailyStatisticsRequest.builder()
                .trackingUrl("www.abc.com/asdffezcvvfafa")
                .date(LocalDate.of(2023,10,7))
                .build();
    }

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