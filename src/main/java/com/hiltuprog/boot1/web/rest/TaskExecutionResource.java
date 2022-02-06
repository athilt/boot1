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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hiltuprog.boot1.dto.TaskExecutionDTO;
import com.hiltuprog.boot1.service.TaskExecutionService;

@RestController
@RequestMapping("/api/taskexecution")
public class TaskExecutionResource {
    private static final List<String> ALLOWED_ORDERED_PROPERTIES = Collections.unmodifiableList(Arrays.asList("id"));

    private final Logger log = LoggerFactory.getLogger(TaskExecutionResource.class);

    @Value("${boot1.clientApp.name}")
    private String applicationName;
    @Autowired
    private  TaskExecutionService taskExecutionService;

    @CrossOrigin(origins = "*")
    @GetMapping("/execution/{id}")
    public ResponseEntity<TaskExecutionDTO> getTaskExecution(@PathVariable Long id) {
        log.info("REST request to get TaskExecution : {}", id);
        return new ResponseEntity(taskExecutionService.findById(id)
        .map(TaskExecutionDTO::new), HttpStatus.OK);
     }
    /*
    @GetMapping("/begin/{courseId}/{userId}")
	public void begin(@PathVariable Long courseId, @PathVariable Long userId) throws Exception {
		log.info("REST request begin executing task for user " + userId + " in course " + courseId);
		courseService.begin(courseId, userId);
	}
	*/
    //addTaskProgress(TaskProgressDTO)
    //getAllTaskProgresses(taskExecutionId)
    //getTaskProgresses(taskExecutionId, startDate, endDate)
}
