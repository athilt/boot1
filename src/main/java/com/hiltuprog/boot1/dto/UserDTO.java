package com.hiltuprog.boot1.dto;

import java.util.List;

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
public class UserDTO {

    private Long id;

    private String login;

    private String firstName;

    private String lastName;
    
    private String email;
    
    private List<Course> courses;
        

    public UserDTO() {
        // Empty constructor needed for Jackson.
    }

    public UserDTO(User u) {
    	this.id = u.getId();
        this.login = u.getLogin();
        this.firstName = u.getFirstName();
        this.lastName = u.getLastName();
        this.email = u.getEmail();
        this.courses = u.getCourses();
    }
/*
    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }
   
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    */
    public void addCourse(Course c)
    {
    	
    }
    
    public Long getId()
    {
    	return id;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserDTO{" +
            "login='" + login + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + 
            "}";
    }
}
