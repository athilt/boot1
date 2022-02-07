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
import com.hiltuprog.boot1.repository.UserRepository;
import com.hiltuprog.boot1.service.CourseService;
import com.hiltuprog.boot1.service.UserService;

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
@RequestMapping("/api/users")
public class UserResource {
    private static final List<String> ALLOWED_ORDERED_PROPERTIES = Collections.unmodifiableList(Arrays.asList("id"));

    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    @Value("${boot1.clientApp.name}")
    private String applicationName;

    @Autowired
    private  UserService userService;
    
    @Autowired
    private UserAssembler userAssembler;
    
    @Autowired
    private CourseAssembler courseAssembler;

    @CrossOrigin(origins = "*")
    @GetMapping("/addcourse/{courseId}/{userId}")
    public void addCourse(@PathVariable Long courseId, @PathVariable Long userId) throws Exception {
        log.info("REST request to add user " + userId + " to course " + courseId);
        userService.addCourse(courseId, userId);
    }
    
    @CrossOrigin(origins = "*")
    @PostMapping("")
    public ResponseEntity<User> create(@Valid @RequestBody User user) throws Exception {
        log.info("REST request to create User : ", user.toString());
        if (user.getId() != null) {
            throw new Exception("A new user cannot already have an ID");
        } else {
            user = userService.create(user);
            return ResponseEntity.created(new URI("/api/users/"))
                    //.headers(HeaderUtil..createAlert(applicationName,  "userManagement.created", newUser.getLogin()))
                    .body(user);
        }
    }

    /**
     * {@code GET /users/bylogin:login} : get the "login" user.
     *
     * @param login the login of the user to find.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the "login" user, or with status {@code 404 (Not Found)}.
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/bylogin{login}")
    public ResponseEntity<User> getUserByLogin(@PathVariable String login) {
        log.info("REST request to get User : {}", login);
        return new ResponseEntity(userService.findByLogin(login), HttpStatus.OK);
    }
    
    /**
     * {@code GET /users/bylogin:login} : get the "login" user.
     *
     * @param login the login of the user to find.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the "login" user, or with status {@code 404 (Not Found)}.
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public EntityModel<User> one(@PathVariable Long id) {
        log.info("REST request to get User : {}", id);
        User user = userService.findById(id).get(); // .orElseThrow(() -> new Exception(Long.toString(id)));
        return userAssembler.toModel(user);
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping("")
    public CollectionModel<EntityModel<User>> all() {
		log.info("REST request to get all users : " + " applicationName: " + applicationName);
		List<EntityModel<User>> items = userService.findAll().stream()
				.map(userAssembler::toModel).collect(Collectors.toList());
		Link link = WebMvcLinkBuilder.linkTo(CourseResource.class).withSelfRel();
		return CollectionModel.of(items, link);
	}
    
    /* Mapping to DTO's is temporary fix to be removed
     * 
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/courses/{userId}")
    public CollectionModel<EntityModel<Course>> courses(@PathVariable Long userId) {
    	log.info("REST request to get all courses for user " + userId);
        List <EntityModel<Course>> itemList = userService.getCourses(userId).stream()
        		.map(courseAssembler::toModel).collect(Collectors.toList());
        Link link = WebMvcLinkBuilder.linkTo(UserResource.class).withSelfRel();
        return CollectionModel.of(itemList, link);
    }
}
