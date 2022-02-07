package com.hiltuprog.boot1.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//import io.github.jhipster.security.RandomUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hiltuprog.boot1.domain.Course;
import com.hiltuprog.boot1.domain.Task;
import com.hiltuprog.boot1.domain.TaskExecution;
//import com.hiltuprog.boot1.config.Constants;
//import com.hiltuprog.boot1.domain.Authority;
import com.hiltuprog.boot1.domain.User;
import com.hiltuprog.boot1.dto.CourseDTO;
import com.hiltuprog.boot1.dto.UserDTO;
import com.hiltuprog.boot1.repository.CourseRepository;
import com.hiltuprog.boot1.repository.TaskExecutionRepository;
import com.hiltuprog.boot1.repository.TaskRepository;
//import com.hiltuprog.boot1.repository.AuthorityRepository;
//import com.hiltuprog.boot1.repository.PersistentTokenRepository;
import com.hiltuprog.boot1.repository.UserRepository;
//import com.hiltuprog.boot1.repository.search.UserSearchRepository;
//import com.hiltuprog.boot1.security.AuthoritiesConstants;
//import com.hiltuprog.boot1.security.SecurityUtils;
//import com.hiltuprog.boot1.service.dto.UserDTO;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class CourseService {

	private final Logger log = LoggerFactory.getLogger(CourseService.class);

	private final CourseRepository courseRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private TaskRepository taskRepository;

	public CourseService(CourseRepository courseRepository) { // , PasswordEncoder passwordEncoder, UserSearchRepository
																// userSearchRepository, PersistentTokenRepository
																// persistentTokenRepository, AuthorityRepository
																// authorityRepository, CacheManager cacheManager) {
		this.courseRepository = courseRepository;
	}

	public Optional<Course> findById(Long courseId) {
		return courseRepository.findOneById(courseId);
	}

	public Course createCourse(CourseDTO courseDTO) {
		Course course = new Course();
		course.setTitle(courseDTO.getTitle());
		course.setDescription(courseDTO.getDescription());
		// This is the light version.Create implicit task:
		Task task = new Task();
		task.setTitle(course.getTitle());
		task = taskRepository.save(task);
		course.setTasks(Arrays.asList(task));
		courseRepository.save(course);
		log.debug("Created Course: ", course);
		return course;
	}

	public Course updateCourse(CourseDTO courseDTO) {
		Course course = new Course();
		course.setId(courseDTO.getId());
		course.setTitle(courseDTO.getTitle());
		course.setDescription(courseDTO.getDescription());
		courseRepository.save(course);
		log.debug("Updated Course: ", course);
		return course;
	}

	public Course deleteCourse(CourseDTO courseDTO) {
		Course course = new Course();
		course.setId(courseDTO.getId());
		courseRepository.delete(course);
		log.debug("Delete Course: ", course);
		return course;
	}
/*
	public void addUser(Long courseId, Long userId) {
		// TODO: Some error checking, please!
		Course course = courseRepository.findById(courseId).get();
		User user = userService.findById(userId).get();
		course.getUsers().add(user);
		// This is the light version.Create use implicit, create implicit TaskExecution:
		Task task = course.getTasks().get(0);
		TaskExecution te = new TaskExecution();
		te.setTask(task);
		te.setUser(user);
		taskExecutionRepository.save(te);
	}
*/
	public void addTask(Long courseId, Long taskId) {
		courseRepository.findById(courseId).get().getTasks().add(taskRepository.findById(taskId).get());
	}

	public List<Course> findAll() {
		return courseRepository.findAll();
	}
}