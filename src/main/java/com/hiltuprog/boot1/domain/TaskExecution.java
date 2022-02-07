package com.hiltuprog.boot1.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="task_execution")
@Getter
@Setter
public class TaskExecution {
	@Id
    @GeneratedValue
	private Long id;
	
	private String title;
	
	private String description;
	
    private LocalDate created;
    
    @ManyToOne
    private User user;
    
    @ManyToOne
    private Task task;
    
    @OneToMany
    private List<TaskProgress> progress;
}
