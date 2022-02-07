package com.hiltuprog.boot1.web.rest;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hiltuprog.boot1.domain.Task;
import com.hiltuprog.boot1.domain.TaskProgress;
import com.hiltuprog.boot1.dto.CourseDTO;
import com.hiltuprog.boot1.dto.TaskDTO;
import com.hiltuprog.boot1.dto.TaskProgressDTO;
import com.hiltuprog.boot1.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskResource {
	private static final List<String> ALLOWED_ORDERED_PROPERTIES = Collections.unmodifiableList(Arrays.asList("id"));

	private final Logger log = LoggerFactory.getLogger(TaskResource.class);

	@Value("${boot1.clientApp.name}")
	private String applicationName;

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private TaskAssembler assembler;

	@CrossOrigin(origins = "*")
	@GetMapping("")
	public CollectionModel<EntityModel<Task>> all() {
		log.info("REST request to get all Courses : {}" + " applicationName: " + applicationName);
		List<EntityModel<Task>> dtoList = taskService.findAll().stream()
				.map(assembler::toModel).collect(Collectors.toList());
		Link link = WebMvcLinkBuilder.linkTo(CourseResource.class).withSelfRel();
		return CollectionModel.of(dtoList, link);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/{id}")
	public ResponseEntity<Task> one(@PathVariable Long id) {
		log.info("GET request to get Task : {}", id);
		return new ResponseEntity(taskService.findById(id), HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("")
	public ResponseEntity<Task> create(@Valid @RequestBody Task item) throws Exception {
		log.info("POST request to save TaskProgress ", item);
		if (item.getId() != null) {
			throw new Exception("A new TaskProgress cannot already have an ID");
		}
		item = taskService.create(item);
		return ResponseEntity.created(new URI("/api/tasks/"))
				.body(item);
	}
}
