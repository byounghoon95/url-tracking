package com.example.urltracking;

import com.example.urltracking.api.controller.dailycount.DailyCountApiController;
import com.example.urltracking.api.controller.dailycount.request.DailyStatisticsRequest;
import com.example.urltracking.api.controller.urls.UrlsApiController;
import com.example.urltracking.api.controller.urls.request.UrlCountRequest;
import com.example.urltracking.api.controller.urls.request.UrlCreateRequest;
import com.example.urltracking.api.controller.urls.request.UrlUpdateRequest;
import com.example.urltracking.api.service.dailycount.DailyCountService;
import com.example.urltracking.api.service.urls.UrlsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

@ActiveProfiles("test")
@WebMvcTest(controllers = {
        UrlsApiController.class,
        DailyCountApiController.class,
})
public abstract class CommonControllerTest {
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @MockBean
    protected UrlsService urlsService;
    @MockBean
    protected DailyCountService dailyCountService;

    protected UrlCreateRequest urlCreateRequest() {
        return UrlCreateRequest.builder()
                .url("www.abc.com/1/2")
                .build();
    }

    protected UrlUpdateRequest urlUpdateRequest() {
        return UrlUpdateRequest.builder()
                .trackingUrl("www.abc.com/asdffezcvvfafa")
                .build();
    }

    protected UrlCountRequest urlCountRequest() {
        return UrlCountRequest.builder()
                .trackingUrl("www.abc.com/asdffezcvvfafa")
                .build();
    }

    protected DailyStatisticsRequest dailyStatisticsRequest() {
        return DailyStatisticsRequest.builder()
                .trackingUrl("www.abc.com/asdffezcvvfafa")
                .date(LocalDate.of(2023,10,7))
                .build();
    }
}
