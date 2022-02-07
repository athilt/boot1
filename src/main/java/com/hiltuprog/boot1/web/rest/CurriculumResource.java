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

import com.hiltuprog.boot1.domain.Course;
import com.hiltuprog.boot1.domain.Curriculum;
import com.hiltuprog.boot1.dto.CourseDTO;
import com.hiltuprog.boot1.dto.CurriculumDTO;
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

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public ResponseEntity<CurriculumDTO> getCurriculum(@PathVariable Long id) {
        log.info("REST request to get Curriculum : {}", id);
        return new ResponseEntity(curriculumService.findOneById(id)
        .map(CurriculumDTO::new), HttpStatus.OK);
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping("")
    public ResponseEntity<CurriculumDTO> getAllCurriculums() {
        log.info("REST request to get all Curriculums : {}");
        return new ResponseEntity(curriculumService.findAll().stream()
        		.map(CurriculumDTO::new).collect(Collectors.toList()), HttpStatus.OK);
    }
    
    @CrossOrigin(origins = "*")
    @PostMapping("")
    public ResponseEntity<Curriculum> createCurriculum(@Valid @RequestBody CurriculumDTO curriculumDTO) throws Exception {
        log.info("REST request to create Curriculum : {}", curriculumDTO);

        if (curriculumDTO.getId() != null) {
            throw new Exception("A new course cannot already have an ID");
        } else {
        	Curriculum newCurriculum = curriculumService.createCurriculum(curriculumDTO);
            return ResponseEntity.created(new URI("/api/curriculums/"))
                    //.headers(HeaderUtil..createAlert(applicationName,  "userManagement.created", newUser.getLogin()))
                    .body(newCurriculum);
        }
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
