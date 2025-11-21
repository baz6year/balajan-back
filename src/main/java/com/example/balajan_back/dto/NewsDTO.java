package com.example.balajan_back.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewsDTO {
    private Long id;
    private String title;
    private String slug;
    private OffsetDateTime publishedAt;
    private String imageUrl;
    private String excerpt;
    private String content;

    public String getDate() {
        return publishedAt != null
                ? publishedAt.toLocalDate().toString()  // формат типа "2024-12-23"
                : null;
    }
}


