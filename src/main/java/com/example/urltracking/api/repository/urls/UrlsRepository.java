package com.example.urltracking.api.repository.urls;

import com.example.urltracking.entity.urls.Urls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface UrlsRepository extends JpaRepository<Urls,Long> {
    boolean existsByTrackingUrl(String trackingUrl);

    Optional<Urls> findByTrackingUrl(String trackingUrl);

    @Query(value =
            "SELECT NEW com.example.urltracking.entity.urls.Urls(u.url, u.trackingUrl, u.totalCount, d.dailyCount, d.date) " +
            "FROM Urls u " +
            "LEFT JOIN DailyCount d ON u.trackingUrl = d.trackingUrl " +
            "WHERE u.trackingUrl = :trackingUrl " +
            "AND d.date = CURRENT_DATE "
    )
    Optional<Urls> findCountByTrackingUrl(@Param("trackingUrl") String trackingUrl);
}
