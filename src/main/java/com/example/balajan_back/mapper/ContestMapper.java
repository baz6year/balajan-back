package com.example.balajan_back.mapper;

import com.example.balajan_back.dto.ContestDTO;
import com.example.balajan_back.entity.Contest;

public class ContestMapper {
    public static ContestDTO toDto(Contest c) {
        if (c == null) return null;
        return ContestDTO.builder()
                .id(c.getId())
                .title(c.getTitle())
                .slug(c.getSlug())
                .excerpt(c.getExcerpt())
                .imageUrl(c.getImageUrl())
                .category(c.getCategory())
                .startDate(c.getStartDate())
                .endDate(c.getEndDate())
                .isFeatured(c.isFeatured())
                .stages(c.getStages())
                .content(c.getContent())
                .build();
    }

    public static Contest toEntity(ContestDTO d) {
        if (d == null) return null;
        return Contest.builder()
                .id(d.getId())
                .title(d.getTitle())
                .slug(d.getSlug())
                .excerpt(d.getExcerpt())
                .imageUrl(d.getImageUrl())
                .category(d.getCategory())
                .startDate(d.getStartDate())
                .endDate(d.getEndDate())
                .isFeatured(d.isFeatured())
                .stages(d.getStages())
                .content(d.getContent())
                .build();
    }
}
