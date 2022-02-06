package com.hiltuprog.boot1.dto;

import com.hiltuprog.boot1.domain.Course;
import com.hiltuprog.boot1.domain.TaskProgress;

import lombok.Getter;
import lombok.Setter;

//import javax.validation.constraints.Size;

/**
 * A DTO representing a user, with his authorities.
 */
@Getter
@Setter
public class TaskProgressDTO {

    private Long id;

    private String title;

    private String content;
    
    public TaskProgressDTO() {
        // Empty constructor needed for Jackson.
    }

    public TaskProgressDTO(TaskProgress t) {
    	this.id = t.getId();
        this.title = t.getTitle();
        this.content = t.getContent();
    }
    @Override
    public String toString() {
        return "TaskProgressDTO {" +
        	"title=" + title +
            "content=" + content + 
            "}";
    }
}
