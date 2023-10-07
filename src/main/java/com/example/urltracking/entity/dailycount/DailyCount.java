package com.example.urltracking.entity.dailycount;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "daily_count")
@Entity
public class DailyCount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "daily_count_id")
    private Long id;

    @Column(name = "daily_url")
    private String url;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "daily_count")
    private int count = 0;

    @Builder
    public DailyCount(String url, LocalDate date, int count) {
        this.url = url;
        this.date = date;
        this.count = count;
    }
}
