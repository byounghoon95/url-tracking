package com.example.urltracking.api.controller.urls;

import com.example.urltracking.CommonControllerTest;
import com.example.urltracking.api.controller.urls.request.UrlCountRequest;
import com.example.urltracking.api.controller.urls.request.UrlCreateRequest;
import com.example.urltracking.api.controller.urls.request.UrlUpdateRequest;
import com.example.urltracking.enums.CodeEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ActiveProfiles("test")
@WebMvcTest
class UrlsApiControllerTest extends CommonControllerTest {

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