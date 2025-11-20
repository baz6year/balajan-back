package com.example.balajan_back.mapper;

import com.example.balajan_back.dto.NewsDTO;
import com.example.balajan_back.entity.News;

public class NewsMapper {
    public static NewsDTO toDto(News e) {
        if (e == null) return null;
        return NewsDTO.builder()
                .id(e.getId())
                .title(e.getTitle())
                .slug(e.getSlug())
                .publishedAt(e.getPublishedAt())
                .imageUrl(e.getImageUrl())
                .excerpt(e.getExcerpt())
                .content(e.getContent())
                .build();
    }

    public static News toEntity(NewsDTO d) {
        if (d == null) return null;
        return News.builder()
                .id(d.getId())
                .title(d.getTitle())
                .slug(d.getSlug())
                .publishedAt(d.getPublishedAt())
                .imageUrl(d.getImageUrl())
                .excerpt(d.getExcerpt())
                .content(d.getContent())
                .build();
    }
}
