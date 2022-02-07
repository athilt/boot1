package com.hiltuprog.boot1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hiltuprog.boot1.domain.TaskExecution;
import com.hiltuprog.boot1.domain.User;

/**
 * Spring Data JPA repository for the {@link User} entity.
 */
@Repository
public interface TaskExecutionRepository extends JpaRepository<TaskExecution, Long> {

}
