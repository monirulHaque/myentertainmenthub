package net.monirul.springboot.repository;

import net.monirul.springboot.models.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeriesRepository extends JpaRepository<Series, Long> {
    Series findByApiId(String apiId);
    boolean existsByApiId(String apiId);
}