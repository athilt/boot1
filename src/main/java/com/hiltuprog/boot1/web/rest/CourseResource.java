package com.hiltuprog.boot1.web.rest;

//import javax.validation.Valid;
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
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hiltuprog.boot1.domain.Course;
import com.hiltuprog.boot1.domain.User;
import com.hiltuprog.boot1.dto.CourseDTO;
import com.hiltuprog.boot1.service.CourseService;

//import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing users.
 * <p>
 * This class accesses the {@link User} entity, and needs to fetch its collection of authorities.
 * <p>
 * For a normal use-case, it would be better to have an eager relationship between User and Authority,
 * and send everything to the client side: there would be no View Model and DTO, a lot less code, and an outer-join
 * which would be good for performance.
 * <p>
 * We use a View Model and a DTO for 3 reasons:
 * <ul>
 * <li>We want to keep a lazy association between the user and the authorities, because people will
 * quite often do relationships with the user, and we don't want them to get the authorities all
 * the time for nothing (for performance reasons). This is the #1 goal: we should not impact our users'
 * application because of this use-case.</li>
 * <li> Not having an outer join causes n+1 requests to the database. This is not a real issue as
 * we have by default a second-level cache. This means on the first HTTP call we do the n+1 requests,
 * but then all authorities come from the cache, so in fact it's much better than doing an outer join
 * (which will get lots of data from the database, for each HTTP call).</li>
 * <li> As this manages users, for security reasons, we'd rather have a DTO layer.</li>
 * </ul>
 * <p>
 * Another option would be to have a specific JPA entity graph to handle this case.
 */
@RestController
@RequestMapping("/api")
public class CourseResource {
    private static final List<String> ALLOWED_ORDERED_PROPERTIES = Collections.unmodifiableList(Arrays.asList("id", "login", "firstName", "lastName", "email", "activated", "langKey"));

    private final Logger log = LoggerFactory.getLogger(CourseResource.class);


    private final CourseAssembler assembler;

    CourseResource(CourseAssembler assembler) {
      this.assembler = assembler;
    }
    
    @Value("${boot1.clientApp.name}")
    private String applicationName;
    @Autowired
    private  CourseService courseService;

    @CrossOrigin(origins = "*")
    @PostMapping("/courses")
    public ResponseEntity<Course> createCourse(@Valid @RequestBody CourseDTO courseDTO) throws Exception {
        log.info("REST request to save Course : {}", courseDTO);

        if (courseDTO.getId() != null) {
            throw new Exception("A new course cannot already have an ID");
        } else {
            Course newCourse = courseService.createCourse(courseDTO);
            return ResponseEntity.created(new URI("/api/users/"))
                    //.headers(HeaderUtil..createAlert(applicationName,  "userManagement.created", newUser.getLogin()))
                    .body(newCourse);
        }
    }
    
	/*
	 * @CrossOrigin(origins = "*")
	 * 
	 * @GetMapping("/courses/{id}") public ResponseEntity<UserDTO> one(@PathVariable
	 * Long id) { log.info("REST request to get Course : {}", id); return new
	 * ResponseEntity(courseService.findById(id) .map(CourseDTO::new),
	 * HttpStatus.OK); }
	 */

    @CrossOrigin(origins = "*")
    @GetMapping("/courses/{id}")
    EntityModel<CourseDTO> one(@PathVariable Long id) {
      CourseDTO course = new CourseDTO(courseService.findById(id).get()); // .orElseThrow(() -> new Exception(Long.toString(id)));
      return assembler.toModel(course);
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping("/courses")
    public CollectionModel<EntityModel<CourseDTO>> all() {
        log.info("REST request to get all Courses : {}" + " applicationName: " + applicationName );
        List <EntityModel<CourseDTO>> courseList = courseService.findAll().stream()
        		.map(CourseDTO::new).map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(courseList);
        		//linkTo(methodOn)
        //return new ResponseEntity(courseService.findAll().stream()
        //		.map(CourseDTO::new).collect(Collectors.toList()), HttpStatus.OK);
    }
   
    /**
     * {@code GET /courses/:login} : get all courses of "login" user.
     *
     * @param login the login of the user to find.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the course list of the 
     * "login" user, or with status {@code 404 (Not Found)}.
     */
	/*
	 * @CrossOrigin(origins = "*")
	 * 
	 * @GetMapping("/courses/{userId}") public ResponseEntity<CourseDTO>
	 * getCoursesByUser(@PathVariable Long userId) {
	 * log.info("REST request to get all courses for User : " + userId );
	 * 
	 * return new ResponseEntity(courseService.getAllByUser(userId).stream()
	 * .map(CourseDTO::new).collect(Collectors.toList()), HttpStatus.OK); }
	 */ 
   //addCourse(CourseDTO) //Creates and adds given Course
    

    //addUser(userId)
    @GetMapping("/courses/adduser/{courseId}/{userId}")
    public void addUser(@PathVariable Long courseId, @PathVariable Long userId) throws Exception {
        log.info("REST request to add user " + userId + " to course " + courseId);
        courseService.addUser(courseId, userId);
    }
    //removeUser(userId)
    //addTask(TaskDTO)
    @GetMapping("/courses/addtask/{courseId}/{taskId}")
    public void addTask(@PathVariable Long courseId, @PathVariable Long taskId) throws Exception {
        log.info("REST request to add task " + taskId + " to course " + courseId);
        courseService.addTask(courseId, taskId);
    }
}
