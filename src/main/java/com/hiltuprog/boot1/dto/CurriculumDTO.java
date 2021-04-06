package com.hiltuprog.boot1.dto;

import com.hiltuprog.boot1.domain.Curriculum;

import lombok.Getter;
import lombok.Setter;

//import javax.validation.constraints.Size;

/**
 * A DTO representing a user, with his authorities.
 */
@Getter
@Setter
public class CurriculumDTO {

    private Long id;

    private String title;

    private String description;
    
    public CurriculumDTO() {
        // Empty constructor needed for Jackson.
    }

    public CurriculumDTO(Curriculum c) {
        this.id = c.getId();
        this.title = c.getTitle();
        this.description = c.getDescription();
    }
    
    @Override
    public String toString() {
        return "CurriculumDTO {" +
        	"title=" + title +
            "description=" + description + 
            "}";
    }
}
