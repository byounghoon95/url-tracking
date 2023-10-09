package com.example.urltracking.api.service.urls;

import com.example.urltracking.api.repository.dailycount.DailyCountRepository;
import com.example.urltracking.api.repository.urls.UrlsRepository;
import com.example.urltracking.api.service.urls.request.UrlCountServiceRequest;
import com.example.urltracking.api.service.urls.request.UrlCreateServiceRequest;
import com.example.urltracking.api.service.urls.request.UrlUpdateServiceRequest;
import com.example.urltracking.api.service.urls.response.UrlCountResponse;
import com.example.urltracking.api.service.urls.response.UrlCreateResponse;
import com.example.urltracking.api.service.urls.response.UrlUpdateResponse;
import com.example.urltracking.entity.dailycount.DailyCount;
import com.example.urltracking.entity.urls.Urls;
import com.example.urltracking.enums.CodeEnum;
import com.example.urltracking.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UrlsService {
    private final UrlsRepository urlsRepository;
    private final DailyCountRepository dailyCountRepository;

    @Transactional
    public UrlCreateResponse createUrl(UrlCreateServiceRequest request) {
        request.convertUrl();

        String trackingUrl = request.getTrackingUrl();
        while (urlsRepository.existsByTrackingUrl(trackingUrl)) {
            request.convertUrl();
            trackingUrl = request.getTrackingUrl();
        }
        Urls savedUrl = urlsRepository.save(request.toUrlsEntity());
        dailyCountRepository.save(request.toDailyCountEntity());

        return UrlCreateResponse.of(savedUrl);
    }

    @Transactional
    public UrlUpdateResponse updateUrlCount(UrlUpdateServiceRequest request) {
        Urls findUrl = urlsRepository.findByTrackingUrl(request.getTrackingUrl()).orElseThrow(
                () -> new CustomException(CodeEnum.URL_NOTFOUND)
        );

        Optional<DailyCount> dailyCountOpt = dailyCountRepository.findByTrackingUrlWithDate(request.getTrackingUrl());
        DailyCount dailyCount;
        if (dailyCountOpt.isPresent()) {
            dailyCount = dailyCountOpt.get();
        } else {
            dailyCount = dailyCountRepository.save(request.toDailyCountEntity());
        }

        findUrl.addCount();
        dailyCount.addCount();
        findUrl.updateDailyCount(dailyCount.getDailyCount());
        return UrlUpdateResponse.of(findUrl);
    }

    public UrlCountResponse getUrlCount(UrlCountServiceRequest request) {
        urlsRepository.findByTrackingUrl(request.getTrackingUrl()).orElseThrow(
                () -> new CustomException(CodeEnum.URL_NOTFOUND)
        );

        Urls responseUrl = urlsRepository.findCountByTrackingUrl(request.getTrackingUrl()).get();
        return UrlCountResponse.of(responseUrl);
    }
}
