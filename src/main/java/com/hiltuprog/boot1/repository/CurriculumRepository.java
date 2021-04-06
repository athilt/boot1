package com.hiltuprog.boot1.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hiltuprog.boot1.domain.Curriculum;

/**
 * Spring Data JPA repository for the {@link User} entity.
 */
@Repository
public interface CurriculumRepository extends JpaRepository<Curriculum, Long> {

    Optional<Curriculum> findOneById(Long id);
    List<Curriculum> findAll();
}
