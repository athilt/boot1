package com.hiltuprog.boot1.dto;

//import com.hiltuprog.boot1.config.Constants;

//import com.hiltuprog.boot1.domain.Authority;
//import com.hiltuprog.boot1.domain.User;

//import javax.validation.constraints.*;
import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.hiltuprog.boot1.domain.Course;
import com.hiltuprog.boot1.domain.User;

import lombok.Getter;
import lombok.Setter;

//import javax.validation.constraints.Size;

/**
 * A DTO representing a user, with his authorities.
 */
@Getter
@Setter
public class CourseDTO {

    private Long id;

    private String title;

    private String description;
    
    private List<User> users;
 
    public CourseDTO() {
        // Empty constructor needed for Jackson.
    }
    public CourseDTO(Course c) {
    	this.id = c.getId();
        this.title = c.getTitle();
        this.description = c.getDescription();
        //this.users = c.getUsers();
    }
    @Override
    public String toString() {
        return "CourseDTO {" +
        	"title=" + title +
            "description=" + description + 
            "}";
    }
}
