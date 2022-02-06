package com.hiltuprog.boot1.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hiltuprog.boot1.domain.Course;
import com.hiltuprog.boot1.domain.Task;
import com.hiltuprog.boot1.domain.TaskProgress;
import com.hiltuprog.boot1.domain.User;

/**
 * Spring Data JPA repository for the {@link User} entity.
 */
@Repository
public interface TaskProgressRepository extends JpaRepository<TaskProgress, Long> {

    //Optional<TaskProgress> findOneById(Long id);
}
