package com.hiltuprog.boot1.service;

import java.util.Optional;

//import io.github.jhipster.security.RandomUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hiltuprog.boot1.domain.Task;
import com.hiltuprog.boot1.dto.TaskDTO;
import com.hiltuprog.boot1.repository.TaskRepository;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class TaskService {

    private final Logger log = LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository taskRepository;

    //private final PasswordEncoder passwordEncoder;

    //private final UserSearchRepository userSearchRepository;

    //private final PersistentTokenRepository persistentTokenRepository;

   // private final AuthorityRepository authorityRepository;

    //private final CacheManager cacheManager;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    
    public Optional<Task> findById(Long id)
    {
    	return taskRepository.findOneById(id);
    }
}