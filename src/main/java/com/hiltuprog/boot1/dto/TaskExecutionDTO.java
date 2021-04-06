package com.hiltuprog.boot1.dto;

import com.hiltuprog.boot1.domain.Course;
import com.hiltuprog.boot1.domain.TaskExecution;

import lombok.Getter;
import lombok.Setter;

//import javax.validation.constraints.Size;

/**
 * A DTO representing a user, with his authorities.
 */
@Getter
@Setter
public class TaskExecutionDTO {

    private Long id;

    private String title;

    private String content;
    
    public TaskExecutionDTO() {
        // Empty constructor needed for Jackson.
    }

    public TaskExecutionDTO(TaskExecution t) {
    	this.id = t.getId();
        this.title = t.getTitle();
        this.content = t.getContent();
    }
    @Override
    public String toString() {
        return "TaskExecutionDTO {" +
        	"title=" + title +
            "content=" + content + 
            "}";
    }
}
