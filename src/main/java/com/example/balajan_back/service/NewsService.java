package com.example.balajan_back.service;

import com.example.balajan_back.dto.NewsDTO;

import java.util.List;

public interface NewsService {

        List<NewsDTO> getAll();

        NewsDTO getById(Long id);

        NewsDTO getBySlug(String slug);

        NewsDTO create(NewsDTO dto);

        NewsDTO update(Long id, NewsDTO dto);

        void delete(Long id);
    }


