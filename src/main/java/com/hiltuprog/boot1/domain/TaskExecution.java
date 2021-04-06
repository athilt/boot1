package com.hiltuprog.boot1.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;

@Entity
//@Table(name="task_execution")
@Getter
public class TaskExecution {
	@Id
    @GeneratedValue
	private Long id;
	
	private String title;
	
	private String content; //rename to description
	
    private LocalDate created;
    
    @ManyToOne
    private User user;
    
    @ManyToOne
    private Task task;
}
