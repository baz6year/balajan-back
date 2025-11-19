package repository;

import com.example.balajan_back.entity.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PageReposritory extends JpaRepository<Page, Integer> {
    Optional<Page> findBySlug(String slug);
}
