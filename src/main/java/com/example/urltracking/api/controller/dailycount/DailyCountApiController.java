package com.example.urltracking.api.controller.dailycount;

import com.example.urltracking.api.common.response.CommonResponse;
import com.example.urltracking.api.controller.dailycount.request.DailyStatisticsRequest;
import com.example.urltracking.api.service.dailycount.DailyCountService;
import com.example.urltracking.api.service.dailycount.response.DailyStatisticsResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api("DailyCount Controller API")
@RequestMapping("/api/dailycount")
@RequiredArgsConstructor
@RestController
public class DailyCountApiController {

    private final DailyCountService dailyCountService;

    @ApiOperation(value = "7일간의 조회수 통계 데이터 조회", notes = "7일간의 조회수 통계 데이터를 조회한다")
    @GetMapping("/daily/statistic")
    public CommonResponse<List<DailyStatisticsResponse>> getDailyStatistics(@RequestBody @Valid DailyStatisticsRequest request) {
        return new CommonResponse<>(dailyCountService.getDailyStatistics(request.toServiceRequest()));
    }

}
