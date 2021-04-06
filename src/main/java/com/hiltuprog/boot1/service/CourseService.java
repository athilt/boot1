package com.hiltuprog.boot1.service;

import java.util.ArrayList;
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
//import com.hiltuprog.boot1.config.Constants;
//import com.hiltuprog.boot1.domain.Authority;
import com.hiltuprog.boot1.domain.User;
import com.hiltuprog.boot1.dto.CourseDTO;
import com.hiltuprog.boot1.dto.UserDTO;
import com.hiltuprog.boot1.repository.CourseRepository;
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
    private TaskService taskService;

    //private final PasswordEncoder passwordEncoder;

    //private final UserSearchRepository userSearchRepository;

    //private final PersistentTokenRepository persistentTokenRepository;

   // private final AuthorityRepository authorityRepository;

    //private final CacheManager cacheManager;

    public CourseService(CourseRepository courseRepository) { //, PasswordEncoder passwordEncoder, UserSearchRepository userSearchRepository, PersistentTokenRepository persistentTokenRepository, AuthorityRepository authorityRepository, CacheManager cacheManager) {
        this.courseRepository = courseRepository;
    }
    
	
	 public List<Course> getAllByUser(Long id) {
	     return courseRepository.findByUsersId(id); 
	  }
	
	 public Optional<Course> findById(Long courseId)
	 {
		 return courseRepository.findOneById(courseId);
	 }
    
    public Course createCourse(CourseDTO courseDTO) {
        Course course = new Course();
        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());
        
        courseRepository.save(course);
        
        log.debug("Created Course: ", course);
        return course;
    }
    
    public void addUser(Long courseId, Long userId)
	{
		//TODO: Some error checking, please!
		courseRepository.findById(courseId).get()
		.getUsers().add(userService.findById(userId).get());
	}
    
    public void addTask(Long courseId, Long taskId)
    {
    	courseRepository.findById(courseId).get()
		.getTasks().add(taskService.findById(taskId).get());
    }
    
    public List<Course> findAll()
    {
    	return courseRepository.findAll();
    }
}