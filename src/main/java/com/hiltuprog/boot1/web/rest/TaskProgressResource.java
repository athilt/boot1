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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hiltuprog.boot1.domain.TaskProgress;
import com.hiltuprog.boot1.service.TaskProgressService;

@RestController
@RequestMapping("/api/progress")
public class TaskProgressResource {
    private static final List<String> ALLOWED_ORDERED_PROPERTIES = Collections.unmodifiableList(Arrays.asList("id"));

    private final Logger log = LoggerFactory.getLogger(TaskProgressResource.class);

    @Value("${boot1.clientApp.name}")
    private String applicationName;

    @Autowired
    private  TaskProgressService taskProgressService;
    
    @Autowired
	private TaskProgressAssembler assembler;

    @CrossOrigin(origins = "*")
	@GetMapping("")
	public CollectionModel<EntityModel<TaskProgress>> all() {
		log.info("REST request to get all TaskProgresses : {}" + " applicationName: " + applicationName);
		List<EntityModel<TaskProgress>> itemList = taskProgressService.findAll().stream()
				.map(assembler::toModel).collect(Collectors.toList());
		Link link = WebMvcLinkBuilder.linkTo(CourseResource.class).withSelfRel();
		return CollectionModel.of(itemList, link);
	}
    
    @CrossOrigin(origins = "*")
	@GetMapping("/{id}")
	public EntityModel<TaskProgress> one(@PathVariable Long id) {
    	TaskProgress item = taskProgressService.findById(id).get(); // .orElseThrow(() -> new															// Exception(Long.toString(id)));
		return assembler.toModel(item);
	}
/*
    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public ResponseEntity<TaskProgressDTO> one(@PathVariable Long id) {
        log.info("GET request to get TaskProgress : {}", id);
        return new ResponseEntity(taskProgressService.findById(id)
        .map(TaskProgressDTO::new), HttpStatus.OK);
    }
   */
    @CrossOrigin(origins = "*")
	@PostMapping("")
	public ResponseEntity<TaskProgress> create(@Valid @RequestBody TaskProgress taskProgress) throws Exception {
		log.info("POST request to save TaskProgress : ", taskProgress);
		if (taskProgress.getId() != null) {
			throw new Exception("A new TaskProgress cannot already have an ID");
		}
		taskProgress = taskProgressService.create(taskProgress);
		return ResponseEntity.created(new URI("/api/progress/"))
				.body(taskProgress);
	}
}
