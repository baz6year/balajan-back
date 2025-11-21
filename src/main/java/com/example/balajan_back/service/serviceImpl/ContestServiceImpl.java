package com.example.balajan_back.service.serviceImpl;

import com.example.balajan_back.dto.ContestDTO;
import com.example.balajan_back.entity.Contest;
import com.example.balajan_back.mapper.ContestMapper;
import com.example.balajan_back.repository.ContestRepository;
import com.example.balajan_back.service.ContestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContestServiceImpl implements ContestService {

    private final ContestRepository contestRepository;

    @Override
    public List<ContestDTO> getAll() {
        return contestRepository.findAll()
                .stream()
                .map(ContestMapper::toDto)
                .toList();
    }

    @Override
    public ContestDTO getById(Long id) {
        Contest contest = contestRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contest not found"));
        return ContestMapper.toDto(contest);
    }

    @Override
    public ContestDTO getBySlug(String slug) {
        Contest contest = contestRepository.findBySlug(slug)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contest not found"));
        return ContestMapper.toDto(contest);
    }

    @Override
    public ContestDTO create(ContestDTO dto) {
        Contest entity = ContestMapper.toEntity(dto);
        // на всякий случай: id при создании должен быть null
        entity.setId(null);
        Contest saved = contestRepository.save(entity);
        return ContestMapper.toDto(saved);
    }

    @Override
    public ContestDTO update(Long id, ContestDTO dto) {
        Contest existing = contestRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contest not found"));

        // Полное обновление (можно сделать и "partial", если захочешь)
        existing.setTitle(dto.getTitle());
        existing.setSlug(dto.getSlug());
        existing.setExcerpt(dto.getExcerpt());
        existing.setImageUrl(dto.getImageUrl());
        existing.setCategory(dto.getCategory());
        existing.setStartDate(dto.getStartDate());
        existing.setEndDate(dto.getEndDate());
        existing.setFeatured(dto.isFeatured());
        existing.setStages(dto.getStages());
        existing.setContent(dto.getContent());

        Contest saved = contestRepository.save(existing);
        return ContestMapper.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        if (!contestRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contest not found");
        }
        contestRepository.deleteById(id);
    }
}
