package com.example.urltracking.api.service.dailycount;

import com.example.urltracking.api.repository.dailycount.DailyCountRepository;
import com.example.urltracking.api.service.dailycount.request.DailyCountServiceRequest;
import com.example.urltracking.api.service.dailycount.response.DailyStatisticsResponse;
import com.example.urltracking.entity.dailycount.DailyCount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class DailyCountService {
    private final DailyCountRepository dailyCountRepository;

    public List<DailyStatisticsResponse> getDailyStatistics(DailyCountServiceRequest request) {
        LocalDate currDate = request.getDate();
        List<DailyCount> dailyCountList = dailyCountRepository.findAllByUrlAndDate(request.getTrackingUrl(), currDate, currDate.minusDays(7));
        return dailyCountList.stream()
                .map(dailyCount -> DailyStatisticsResponse.of(dailyCount))
                .collect(Collectors.toList());
    }
}
