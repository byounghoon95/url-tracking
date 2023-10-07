package com.example.urltracking.entity.urls;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "urls")
@Entity
public class Urls {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "url_id")
    private Long id;

    @Column(name = "url")
    private String url;

    @Column(name = "tracking_url")
    private String trackingUrl;

    @Column(name = "id_deleted")
    private boolean isDeleted = false;

    @Column(name = "total_count")
    private int totalCount = 0;

    @Transient
    private int dailyCount = 0;

    @Builder
    public Urls(String url, String trackingUrl, int totalCount, int dailyCount) {
        this.url = url;
        this.trackingUrl = trackingUrl;
        this.totalCount = totalCount;
        this.dailyCount = dailyCount;
    }

    public void deleteUrl() {
        this.isDeleted = true;
    }

    public void addCount() {
        this.totalCount++;
    }

    public void updateDailyCount(int dailyCount) {
        this.dailyCount = dailyCount;
    }
}
