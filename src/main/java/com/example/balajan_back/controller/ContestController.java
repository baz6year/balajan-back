package com.example.balajan_back.controller;

import com.example.balajan_back.dto.ContestDTO;
import com.example.balajan_back.service.ContestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contests")
@RequiredArgsConstructor

public class ContestController {
        private final ContestService contestService;

        // Для фронта: список конкурсов
        @GetMapping
        public ResponseEntity<List<ContestDTO>> getAll() {
            return ResponseEntity.ok(contestService.getAll());
        }

        // Для фронта: получить по ID (если вдруг нужно)
        @GetMapping("/{id}")
        public ResponseEntity<ContestDTO> getById(@PathVariable Long id) {
            return ResponseEntity.ok(contestService.getById(id));
        }

        // Основной вариант для фронта: получить по slug
        @GetMapping("/slug/{slug}")
        public ResponseEntity<ContestDTO> getBySlug(@PathVariable String slug) {
            return ResponseEntity.ok(contestService.getBySlug(slug));
        }

        // Создание (по-хорошему только для админа)
        @PostMapping
        public ResponseEntity<ContestDTO> create(@RequestBody ContestDTO dto) {
            ContestDTO created = contestService.create(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        }

        // Обновление по id
        @PutMapping("/{id}")
        public ResponseEntity<ContestDTO> update(
                @PathVariable Long id,
                @RequestBody ContestDTO dto
        ) {
            ContestDTO updated = contestService.update(id, dto);
            return ResponseEntity.ok(updated);
        }

        // Удаление по id
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable Long id) {
            contestService.delete(id);
            return ResponseEntity.noContent().build();
        }
    }

