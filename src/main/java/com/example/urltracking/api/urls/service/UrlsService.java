package com.example.urltracking.api.urls.service;

import com.example.urltracking.api.urls.repository.UrlsRepository;
import com.example.urltracking.api.urls.service.request.UrlCreateServiceRequest;
import com.example.urltracking.api.urls.service.response.UrlCreateResponse;
import com.example.urltracking.entity.urls.Urls;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UrlsService {
    private final UrlsRepository urlsRepository;

    @Transactional
    public UrlCreateResponse createUrl(UrlCreateServiceRequest request) {
        request.convertUrl();

        String trackingUrl = request.getTrackingUrl();
        while (urlsRepository.existsByTrackingUrl(trackingUrl)) {
            request.convertUrl();
            trackingUrl = request.getTrackingUrl();
        }
        Urls savedUrl = urlsRepository.save(request.toEntity());
        return UrlCreateResponse.of(savedUrl);
    }

}
