package net.monirul.springboot.repository;

import net.monirul.springboot.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findByApiId(String apiId);
    boolean existsByApiId(String apiId);
}
