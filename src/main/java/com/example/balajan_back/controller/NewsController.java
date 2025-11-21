package com.example.balajan_back.controller;

import com.example.balajan_back.dto.NewsDTO;
import com.example.balajan_back.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {
        private final NewsService newsService;

        // список новостей
        @GetMapping
        public ResponseEntity<List<NewsDTO>> getAll() {
            return ResponseEntity.ok(newsService.getAll());
        }

        // по id (если нужно)
        @GetMapping("/{id}")
        public ResponseEntity<NewsDTO> getById(@PathVariable Long id) {
            return ResponseEntity.ok(newsService.getById(id));
        }

        // основной вариант для фронта: по slug
        @GetMapping("/slug/{slug}")
        public ResponseEntity<NewsDTO> getBySlug(@PathVariable String slug) {
            return ResponseEntity.ok(newsService.getBySlug(slug));
        }

        // создать новость
        @PostMapping
        public ResponseEntity<NewsDTO> create(@RequestBody NewsDTO dto) {
            NewsDTO created = newsService.create(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        }

        // обновить новость
        @PutMapping("/{id}")
        public ResponseEntity<NewsDTO> update(
                @PathVariable Long id,
                @RequestBody NewsDTO dto
        ) {
            NewsDTO updated = newsService.update(id, dto);
            return ResponseEntity.ok(updated);
        }

        // удалить новость
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable Long id) {
            newsService.delete(id);
            return ResponseEntity.noContent().build();
        }
    }


