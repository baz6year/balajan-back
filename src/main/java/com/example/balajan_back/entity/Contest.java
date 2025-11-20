package com.example.balajan_back.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "contests")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, unique = true)
    private String slug;

    @Column(columnDefinition = "text")
    private String excerpt;

    @Column(name = "image_url")
    private String imageUrl;

    private String category; // TEACHER / KID / ALL

    @Column(name = "start_date")
    private java.time.LocalDate startDate;

    @Column(name = "end_date")
    private java.time.LocalDate endDate;

    @Column(name = "is_featured")
    private boolean isFeatured;

    @Column(columnDefinition = "jsonb")
    private String stages;

    @Column(columnDefinition = "text")
    private String content;


}

