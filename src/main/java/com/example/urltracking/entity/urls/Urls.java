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

    @Column(name = "id_deleted")
    private boolean isDeleted = false;

    @Column(name = "total_count")
    private int count = 0;

    @Builder
    public Urls(String url, int count) {
        this.url = url;
        this.count = count;
    }

    public void deleteUrl() {
        this.isDeleted = true;
    }
}
