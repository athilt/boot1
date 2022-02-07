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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hiltuprog.boot1.domain.Curriculum;
import com.hiltuprog.boot1.service.CurriculumService;

@RestController
@RequestMapping("/api/curriculums")
public class CurriculumResource {
    private static final List<String> ALLOWED_ORDERED_PROPERTIES = Collections.unmodifiableList(Arrays.asList("id"));
    private final Logger log = LoggerFactory.getLogger(CurriculumResource.class);

    @Value("${boot1.clientApp.name}")
    private String applicationName;

    @Autowired
    private CurriculumService curriculumService;
    
    @Autowired
	private CurriculumAssembler assembler;

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public ResponseEntity<Curriculum> one(@PathVariable Long id) {
        log.info("REST request to get Curriculum : {}", id);
        return new ResponseEntity(curriculumService.findById(id).get(), HttpStatus.OK);
    }
      
    @CrossOrigin(origins = "*")
    @PostMapping("")
    public ResponseEntity<Curriculum> create(@Valid @RequestBody Curriculum curriculum) throws Exception {
        log.info("REST request to create Curriculum : {}", curriculum);

        if (curriculum.getId() != null) {
            throw new Exception("A new course cannot already have an ID");
        } else {
            return ResponseEntity.created(new URI("/api/curriculums/foo"))
                    //.headers(HeaderUtil..createAlert(applicationName,  "userManagement.created", newUser.getLogin()))
                    .body(curriculum);
        }
    }
    
    @CrossOrigin(origins = "*")
	@GetMapping("")
	public CollectionModel<EntityModel<Curriculum>> all() {
		log.info("REST request to get all curriculums : {}" + " applicationName: " + applicationName);
		List<EntityModel<Curriculum>> items = curriculumService.findAll().stream()
				//.map(Course::new)
				.map(assembler::toModel).collect(Collectors.toList());
		Link link = WebMvcLinkBuilder.linkTo(CourseResource.class).withSelfRel();
		return CollectionModel.of(items, link);
    }
 
    @CrossOrigin(origins = "*")
    //addCourse(CourseDTO) //Creates and adds given Course
    @GetMapping("/addcourse/{curriculumId}/{courseId}")
    public void addCourse(@PathVariable Long curriculumId, @PathVariable Long courseId) throws Exception {
    	curriculumService.addCourse(curriculumId, courseId);
    }
    
    //Modify curriculum; This is currently is a stub.
    @PutMapping("")
    public void modify(@PathVariable Long curriculumId, @PathVariable Long courseId) throws Exception {
    	curriculumService.addCourse(curriculumId, courseId);
    }
    //deleteCourse(courseId)
    //getAllCourses()
    //findCourses(String topic)
}
