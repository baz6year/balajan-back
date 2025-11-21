package com.example.balajan_back.repository;

import com.example.balajan_back.entity.Contest;
import com.example.balajan_back.entity.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContestRepository extends JpaRepository<Contest, Long> {
    Optional<Contest> findBySlug(String slug);
}
