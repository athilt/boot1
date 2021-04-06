package com.hiltuprog.boot1.web.rest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hiltuprog.boot1.dto.TaskDTO;
import com.hiltuprog.boot1.service.TaskService;

@RestController
@RequestMapping("/api")
public class TaskResource {
	private static final List<String> ALLOWED_ORDERED_PROPERTIES = Collections.unmodifiableList(Arrays.asList("id"));

	private final Logger log = LoggerFactory.getLogger(TaskResource.class);

	@Value("${boot1.clientApp.name}")
	private String applicationName;

	@Autowired
	private TaskService taskService;

	@CrossOrigin(origins = "*")
	@GetMapping("/tasks/{id}")
	public ResponseEntity<TaskDTO> getTask(@PathVariable Long id) {
		log.info("REST request to get Task : {}", id);
		return new ResponseEntity(taskService.findById(id).map(TaskDTO::new), HttpStatus.OK);
	}
}
