package repository;

import com.example.balajan_back.entity.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PageReposritory extends JpaRepository<Page, Integer> {
    Optional<Page> findBySlug(String slug);
}
