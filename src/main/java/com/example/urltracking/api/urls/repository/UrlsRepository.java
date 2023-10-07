package com.example.urltracking.api.urls.repository;

import com.example.urltracking.entity.urls.Urls;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlsRepository extends JpaRepository<Urls,Long> {
    boolean existsByTrackingUrl(String trackingUrl);
}
