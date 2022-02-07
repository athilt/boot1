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
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
 * This class accesses the {@link User} entity, and needs to fetch its
 * collection of authorities.
 * <p>
 * For a normal use-case, it would be better to have an eager relationship
 * between User and Authority, and send everything to the client side: there
 * would be no View Model and DTO, a lot less code, and an outer-join which
 * would be good for performance.
 * <p>
 * We use a View Model and a DTO for 3 reasons:
 * <ul>
 * <li>We want to keep a lazy association between the user and the authorities,
 * because people will quite often do relationships with the user, and we don't
 * want them to get the authorities all the time for nothing (for performance
 * reasons). This is the #1 goal: we should not impact our users' application
 * because of this use-case.</li>
 * <li>Not having an outer join causes n+1 requests to the database. This is not
 * a real issue as we have by default a second-level cache. This means on the
 * first HTTP call we do the n+1 requests, but then all authorities come from
 * the cache, so in fact it's much better than doing an outer join (which will
 * get lots of data from the database, for each HTTP call).</li>
 * <li>As this manages users, for security reasons, we'd rather have a DTO
 * layer.</li>
 * </ul>
 * <p>
 * Another option would be to have a specific JPA entity graph to handle this
 * case.
 */
@RestController
@RequestMapping("/api/courses")
public class CourseResource {
	private static final List<String> ALLOWED_ORDERED_PROPERTIES = Collections
			.unmodifiableList(Arrays.asList("id", "login", "firstName", "lastName", "email", "activated", "langKey"));

	private final Logger log = LoggerFactory.getLogger(CourseResource.class);

	@Autowired
	private CourseAssembler assembler;

	//CourseResource(CourseAssembler assembler) {
	//	this.assembler = assembler;
	//}

	@Value("${boot1.clientApp.name}")
	private String applicationName;
	@Autowired
	private CourseService courseService;

	@CrossOrigin(origins = "*")
	@PostMapping("")
	public ResponseEntity<Course> create(@Valid @RequestBody CourseDTO courseDTO) throws Exception {
		log.info("REST request to save Course : {}", courseDTO);
		if (courseDTO.getId() != null) {
			throw new Exception("A new course cannot already have an ID");
		}
		Course newCourse = courseService.createCourse(courseDTO);
		return ResponseEntity.created(new URI("/api/courses/"))
				// .headers(HeaderUtil..createAlert(applicationName, "userManagement.created",
				// newUser.getLogin()))
				.body(newCourse);
	}

	@CrossOrigin(origins = "*")
	@DeleteMapping("/{id}")
	public ResponseEntity<Course> courses(@Valid @RequestBody CourseDTO courseDTO, @PathVariable Long id)
			throws Exception {
		log.info("REST request to delete Course : {}", courseDTO);

		if (courseDTO.getId() == null) {
			courseDTO.setId(id);
			if (courseDTO.getId() == null) {
				throw new Exception("Course not found.");
			}
		}
		Course newCourse = courseService.deleteCourse(courseDTO);
		return ResponseEntity.created(new URI("/api/users/"))
				// .headers(HeaderUtil..createAlert(applicationName, "userManagement.created",
				// newUser.getLogin()))
				.body(newCourse);

	}

	@CrossOrigin(origins = "*")
	@GetMapping("/{id}")
	public EntityModel<CourseDTO> one(@PathVariable Long id) {
		CourseDTO one = new CourseDTO(courseService.findById(id).get()); // .orElseThrow(() -> new															// Exception(Long.toString(id)));
		return assembler.toModel(one);
	}

	@CrossOrigin(origins = "*")
	@GetMapping("")
	public CollectionModel<EntityModel<CourseDTO>> all() {
		log.info("REST request to get all Courses : {}" + " applicationName: " + applicationName);
		List<EntityModel<CourseDTO>> items = courseService.findAll().stream().map(CourseDTO::new)
				.map(assembler::toModel).collect(Collectors.toList());
		Link link = WebMvcLinkBuilder.linkTo(CourseResource.class).withSelfRel();
		return CollectionModel.of(items, link);
	}
/*
	@GetMapping("/adduser/{courseId}/{userId}")
	public void addUser(@PathVariable Long courseId, @PathVariable Long userId) throws Exception {
		log.info("REST request to add user " + userId + " to course " + courseId);
		courseService.addUser(courseId, userId);
	}
*/
	@GetMapping("/addtask/{courseId}/{taskId}")
	public void addTask(@PathVariable Long courseId, @PathVariable Long taskId) throws Exception {
		log.info("REST request to add task " + taskId + " to course " + courseId);
		courseService.addTask(courseId, taskId);
	}
}
