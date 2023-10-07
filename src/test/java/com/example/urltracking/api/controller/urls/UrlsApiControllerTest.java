package com.example.urltracking.api.controller.urls;

import com.example.urltracking.api.controller.urls.request.UrlCountRequest;
import com.example.urltracking.api.controller.urls.request.UrlCreateRequest;
import com.example.urltracking.api.controller.urls.request.UrlUpdateRequest;
import com.example.urltracking.api.service.urls.UrlsService;
import com.example.urltracking.api.service.urls.response.UrlCountResponse;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@WebMvcTest
class UrlsApiControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UrlsService urlsService;

    private UrlCreateRequest urlCreateRequest() {
        return UrlCreateRequest.builder()
                .url("www.abc.com/1/2")
                .build();
    }

    private UrlUpdateRequest urlUpdateRequest() {
        return UrlUpdateRequest.builder()
                .trackingUrl("www.abc.com/asdffezcvvfafa")
                .build();
    }

    private UrlCountRequest urlCountRequest() {
        return UrlCountRequest.builder()
                .trackingUrl("www.abc.com/asdffezcvvfafa")
                .date(LocalDate.of(2023,10,7))
                .build();
    }

    @DisplayName("새로운 url을 등록한다")
    @Test
    void create_url() throws Exception {
        //given
        UrlCreateRequest request = urlCreateRequest();

        //when //then
        mockMvc.perform(post("/api/urls/create")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(jsonPath("$.code").value(CodeEnum.SUCCESS.getCode()))
        ;
    }

    @DisplayName("url의 dailycount와 totalcount를 증가시킨다")
    @Test
    void update_url_count() throws Exception {
        //given
        UrlUpdateRequest request = urlUpdateRequest();

        //when //then
        mockMvc.perform(post("/api/urls/update")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(jsonPath("$.code").value(CodeEnum.SUCCESS.getCode()))
        ;
    }

    @DisplayName("url의 dailycount, totalcount, date, url을 조회한다")
    @Test
    void get_url_count() throws Exception {
        //given
        UrlCountRequest request = urlCountRequest();

        //when //then
        mockMvc.perform(get("/api/urls/get/count")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(jsonPath("$.code").value(CodeEnum.SUCCESS.getCode()))
        ;
    }
}