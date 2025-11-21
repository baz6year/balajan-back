package com.example.balajan_back.service;

import com.example.balajan_back.dto.ContestDTO;

import java.util.List;

public interface ContestService {

        List<ContestDTO> getAll();

        ContestDTO getById(Long id);

        ContestDTO getBySlug(String slug);

        ContestDTO create(ContestDTO dto);

        ContestDTO update(Long id, ContestDTO dto);

        void delete(Long id);
}
