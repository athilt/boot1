package com.hiltuprog.boot1.service;

import java.util.List;
import java.util.Optional;

//import io.github.jhipster.security.RandomUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hiltuprog.boot1.domain.Course;
import com.hiltuprog.boot1.domain.TaskProgress;
import com.hiltuprog.boot1.dto.TaskProgressDTO;
import com.hiltuprog.boot1.repository.TaskProgressRepository;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class TaskProgressService {

    private final Logger log = LoggerFactory.getLogger(TaskProgressService.class);

    private final TaskProgressRepository taskProgressRepository;

    public TaskProgressService(TaskProgressRepository taskProgressRepository) {
		this.taskProgressRepository = taskProgressRepository;
	}
    
    public TaskProgress create(TaskProgressDTO dto)
    {
    	TaskProgress taskProgress = new TaskProgress();
    	taskProgress.setTitle(dto.getTitle());
    	taskProgress.setContent(dto.getContent());
    	taskProgressRepository.save(taskProgress);
    	return taskProgress;
    }

    public List<TaskProgress> findAll() {
	     return taskProgressRepository.findAll(); 
	  }
    
	public Optional<TaskProgress> findById(Long id) {
		return taskProgressRepository.findById(id);
	}
}