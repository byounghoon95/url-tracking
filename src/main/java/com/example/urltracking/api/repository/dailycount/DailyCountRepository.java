package com.example.urltracking.api.repository.dailycount;

import com.example.urltracking.entity.dailycount.DailyCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DailyCountRepository extends JpaRepository<DailyCount, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "SELECT d FROM DailyCount d WHERE d.trackingUrl = :trackingUrl AND d.date = CURRENT_DATE")
    Optional<DailyCount> findByTrackingUrlWithDate(@Param("trackingUrl") String trackingUrl);

    @Query(value = "SELECT d FROM DailyCount d WHERE d.trackingUrl = :trackingUrl AND d.date BETWEEN :endDate AND :startDate")
    List<DailyCount> findAllByUrlAndDate(
              @Param("trackingUrl") String trackingUrl
            , @Param("startDate") LocalDate startDate
            , @Param("endDate") LocalDate endDate
    );
}
