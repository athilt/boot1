package com.hiltuprog.boot1.service;

import java.util.Optional;

//import io.github.jhipster.security.RandomUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hiltuprog.boot1.domain.TaskExecution;
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

	public Optional<TaskExecution> findOneById(Long id) {
		return taskExecutionRepository.findOneById(id);
	}
	
}