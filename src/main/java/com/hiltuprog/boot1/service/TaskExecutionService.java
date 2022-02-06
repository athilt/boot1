package com.hiltuprog.boot1.service;

import java.util.List;
import java.util.Optional;

//import io.github.jhipster.security.RandomUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hiltuprog.boot1.domain.Task;
import com.hiltuprog.boot1.domain.TaskExecution;
import com.hiltuprog.boot1.dto.TaskDTO;
import com.hiltuprog.boot1.dto.TaskExecutionDTO;
import com.hiltuprog.boot1.repository.TaskExecutionRepository;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class TaskExecutionService {

    private final Logger log = LoggerFactory.getLogger(TaskExecutionService.class);

    private final TaskExecutionRepository taskExecutionRepository;

    public TaskExecutionService(TaskExecutionRepository taskExecutionRepository) {
		this.taskExecutionRepository = taskExecutionRepository;
	}

	public Optional<TaskExecution> findById(Long id) {
		return taskExecutionRepository.findById(id);
	}

	public List<TaskExecution> findAll() {
	     return taskExecutionRepository.findAll(); 
	  }
   
   public TaskExecution create(TaskExecutionDTO dto) {
       TaskExecution taskExecution = new TaskExecution();
       taskExecution.setId(dto.getId());
       taskExecution.setTitle(dto.getTitle());
       log.debug("Saved taskExecution: {}", taskExecution);
       return taskExecution;
   }
}