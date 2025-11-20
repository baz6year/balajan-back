package com.example.balajan_back.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "news")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, unique = true)
    private String slug;

    @Column(name = "published_at")
    private OffsetDateTime publishedAt;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(columnDefinition = "text")
    private String excerpt;

    @Column(columnDefinition = "text")
    private String content;

}

