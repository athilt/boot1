package com.hiltuprog.boot1.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hiltuprog.boot1.domain.Course;
import com.hiltuprog.boot1.domain.Task;
import com.hiltuprog.boot1.domain.TaskExecution;
import com.hiltuprog.boot1.domain.User;
import com.hiltuprog.boot1.dto.UserDTO;
import com.hiltuprog.boot1.repository.CourseRepository;
import com.hiltuprog.boot1.repository.TaskExecutionRepository;

import com.hiltuprog.boot1.repository.UserRepository;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CourseRepository courseRepository;
    
    @Autowired
	private TaskExecutionRepository taskExecutionRepository;

    public UserService() {};
    
    public Optional<User> findByLogin(String login)
    {
    	return userRepository.findOneByLogin(login);
    }
    
    public Optional<User> findById(Long id)
    {
    	return userRepository.findOneById(id);
    }
    
    public List<User> findAll()
    {
    	List<User> userList = userRepository.findAll();
    	User user0 = userList.get(0);
    	log.info("User0: " + user0.toString());
    	log.info("Courses: " + userList.get(0).getCourses().toString());
    	return userList;
    }
  
    public void addCourse(Long courseId, Long userId) {
		// TODO: Some error checking, please!
		Course course = courseRepository.findOneById(courseId).get();
		User user = userRepository.findOneById(userId).get();
		course.getUsers().add(user);
		// This is the light version.
		//Task task = course.getTasks().get(0);
		TaskExecution te = new TaskExecution();
		//te.setTask(task);
		te.setUser(user);
		taskExecutionRepository.save(te);
	}
 
    public User create(UserDTO dto) {
        User user = new User();
        user.setLogin(dto.getLogin().toLowerCase());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPassword("012345678901234567890123456789012345678901234567890123456789");
        userRepository.save(user);
     
        log.debug("Created User: {}", user);
        return user;
    }
    
    public List<Course> getCourses(Long userId)
    {
    	User user =  userRepository.findOneById(userId).get();
    	return user.getCourses();
    }
}
