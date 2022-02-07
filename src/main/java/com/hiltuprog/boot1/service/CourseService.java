package com.hiltuprog.boot1.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hiltuprog.boot1.domain.Course;
import com.hiltuprog.boot1.domain.Task;
import com.hiltuprog.boot1.repository.CourseRepository;
import com.hiltuprog.boot1.repository.TaskRepository;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class CourseService {

	private final Logger log = LoggerFactory.getLogger(CourseService.class);

	private final CourseRepository courseRepository;

	@Autowired
	private TaskRepository taskRepository;

	public CourseService(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	public Optional<Course> findById(Long courseId) {
		return courseRepository.findById(courseId);
	}

	public Course create(Course course) {
		course.setTitle(course.getTitle());
		course.setDescription(course.getDescription());
		// This is the light version.Create implicit task:
		Task task = new Task();
		task.setTitle(course.getTitle());
		task = taskRepository.save(task);
		course.setTasks(Arrays.asList(task));
		courseRepository.save(course);
		log.debug("Created Course: ", course);
		return course;
	}

	public Course update(Course Course) {
		Course course = new Course();
		course.setId(Course.getId());
		course.setTitle(Course.getTitle());
		course.setDescription(Course.getDescription());
		courseRepository.save(course);
		log.debug("Updated Course: ", course);
		return course;
	}

	public void delete(Course course) {
		log.debug("Delete Course: ", course);
		courseRepository.delete(course);
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