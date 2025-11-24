package com.example.balajan_back.controller.admin;

import com.example.balajan_back.dto.ContestDTO;
import com.example.balajan_back.service.ContestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/contests")
@RequiredArgsConstructor
public class ContestAdminController {

    private final ContestService contestService;

    @GetMapping
    public List<ContestDTO> getAll() {
        return contestService.getAll();
    }

    @GetMapping("/{id}")
    public ContestDTO getById(@PathVariable Long id) {
        return contestService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContestDTO create(@RequestBody ContestDTO dto) {
        return contestService.create(dto);
    }

    @PutMapping("/{id}")
    public ContestDTO update(@PathVariable Long id, @RequestBody ContestDTO dto) {
        return contestService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        contestService.delete(id);
    }
}
