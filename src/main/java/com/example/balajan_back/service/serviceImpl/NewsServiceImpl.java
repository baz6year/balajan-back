package com.example.balajan_back.service.serviceImpl;

import com.example.balajan_back.dto.NewsDTO;
import com.example.balajan_back.entity.News;
import com.example.balajan_back.mapper.NewsMapper;
import com.example.balajan_back.repository.NewsRepository;
import com.example.balajan_back.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
        private final NewsRepository newsRepository;

        @Override
        public List<NewsDTO> getAll() {
            return newsRepository.findAll()
                    .stream()
                    .map(NewsMapper::toDto)
                    .toList();
        }

        @Override
        public NewsDTO getById(Long id) {
            News news = newsRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "News not found"));
            return NewsMapper.toDto(news);
        }

        @Override
        public NewsDTO getBySlug(String slug) {
            News news = newsRepository.findBySlug(slug)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "News not found"));
            return NewsMapper.toDto(news);
        }

        @Override
        public NewsDTO create(NewsDTO dto) {
            News entity = NewsMapper.toEntity(dto);
            entity.setId(null);
            News saved = newsRepository.save(entity);
            return NewsMapper.toDto(saved);
        }

        @Override
        public NewsDTO update(Long id, NewsDTO dto) {
            News existing = newsRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "News not found"));

            existing.setTitle(dto.getTitle());
            existing.setSlug(dto.getSlug());
            existing.setExcerpt(dto.getExcerpt());
            existing.setContent(dto.getContent());
            existing.setImageUrl(dto.getImageUrl());
            // если у тебя поле называется date / publishedAt / что-то ещё — поправь эту строку
            existing.setPublishedAt(dto.getPublishedAt());

            News saved = newsRepository.save(existing);
            return NewsMapper.toDto(saved);
        }

        @Override
        public void delete(Long id) {
            if (!newsRepository.existsById(id)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "News not found");
            }
            newsRepository.deleteById(id);
        }
    }


