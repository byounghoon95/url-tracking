package com.example.urltracking.api.repository.urls;

import com.example.urltracking.entity.urls.Urls;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlsRepository extends JpaRepository<Urls,Long> {
    boolean existsByTrackingUrl(String trackingUrl);

    Optional<Urls> findByTrackingUrl(String trackingUrl);
}
