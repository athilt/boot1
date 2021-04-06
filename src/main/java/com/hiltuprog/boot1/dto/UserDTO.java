package com.hiltuprog.boot1.dto;

import java.util.List;

import com.hiltuprog.boot1.domain.Course;
import com.hiltuprog.boot1.domain.User;

//import javax.validation.constraints.Size;

/**
 * A DTO representing a user, with his authorities.
 */
public class UserDTO {

    private Long id;

    private String login;

    private String firstName;

    private String lastName;
    
    private String email;
        

    public UserDTO() {
        // Empty constructor needed for Jackson.
    }

    public UserDTO(User u) {
        this.login = u.getLogin();
        this.firstName = u.getFirstName();
        this.lastName = u.getLastName();
        this.email = u.getEmail();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void addCourse(Course c)
    {
    	
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
