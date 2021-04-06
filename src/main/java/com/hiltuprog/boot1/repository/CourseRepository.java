package com.hiltuprog.boot1.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hiltuprog.boot1.domain.Course;
import com.hiltuprog.boot1.domain.User;

/**
 * Spring Data JPA repository for the {@link User} entity.
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

	List<Course> findByUsersId(Long userId);
	
	Optional<Course> findOneById(Long courseId);
	
}
