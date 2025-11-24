package com.example.balajan_back.controller.admin;

import com.example.balajan_back.dto.NewsDTO;
import com.example.balajan_back.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/news")
@RequiredArgsConstructor
public class NewsAdminController {

    private final NewsService newsService;

    @GetMapping
    public List<NewsDTO> getAll() {
        return newsService.getAll();
    }

    @GetMapping("/{id}")
    public NewsDTO getById(@PathVariable Long id) {
        return newsService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewsDTO create(@RequestBody NewsDTO dto) {
        return newsService.create(dto);
    }

    @PutMapping("/{id}")
    public NewsDTO update(@PathVariable Long id, @RequestBody NewsDTO dto) {
        return newsService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        newsService.delete(id);
    }
}
