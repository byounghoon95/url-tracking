package com.example.urltracking.api.repository.dailycount;

import com.example.urltracking.entity.dailycount.DailyCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DailyCountRepository extends JpaRepository<DailyCount, Long> {

    @Query(value = "SELECT d FROM DailyCount d WHERE d.trackingUrl = :trackingUrl AND d.date = CURRENT_DATE")
    Optional<DailyCount> findByTrackingUrlWithDate(@Param("trackingUrl") String trackingUrl);
}
