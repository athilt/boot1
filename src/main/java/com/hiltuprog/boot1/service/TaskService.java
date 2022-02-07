package com.hiltuprog.boot1.service;

import java.util.List;
import java.util.Optional;

//import io.github.jhipster.security.RandomUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hiltuprog.boot1.domain.Task;
import com.hiltuprog.boot1.domain.TaskProgress;
import com.hiltuprog.boot1.domain.User;
import com.hiltuprog.boot1.dto.TaskDTO;
import com.hiltuprog.boot1.dto.UserDTO;
import com.hiltuprog.boot1.repository.TaskRepository;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class TaskService {

    private final Logger log = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> findAll() {
	     return taskRepository.findAll(); 
	  }
    
    public Optional<Task> findById(Long id)
    {
    	return taskRepository.findById(id);
    }

    public Task create(Task task) {
        return taskRepository.save(task);
    }

}