package com.example.balajan_back.dto;

import com.example.balajan_back.entity.Stage;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContestDTO {
    private Long id;
    private String title;
    private String slug;
    private String excerpt;
    private String imageUrl;
    private String category;
    private java.time.LocalDate startDate;
    private java.time.LocalDate endDate;
    private boolean isFeatured;
    private List<Stage> stages;
    private String content;
}
