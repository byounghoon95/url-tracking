package com.example.urltracking.api.service.urls;

import com.example.urltracking.api.repository.urls.UrlsRepository;
import com.example.urltracking.api.service.urls.request.UrlCreateServiceRequest;
import com.example.urltracking.api.service.urls.response.UrlCreateResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@ActiveProfiles("test")
@SpringBootTest
class UrlsServiceTest {
    @Autowired
    private UrlsService urlsService;
    @Autowired
    private UrlsRepository urlsRepository;

    protected UrlCreateServiceRequest urlCreateServiceRequest() {
        return UrlCreateServiceRequest.builder()
                .url("www.abc.com/1/2")
                .build();
    }

    @DisplayName("새로운 url을 등록한다")
    @Test
    void create_url() {
        //given
        UrlCreateServiceRequest request = urlCreateServiceRequest();

        //when
        UrlCreateResponse response = urlsService.createUrl(request);

        //then
        assertNotNull(response);
        assertNotNull(response.getTrackingUrl());
        assertEquals(request.getUrl(), response.getUrl());
    }

}