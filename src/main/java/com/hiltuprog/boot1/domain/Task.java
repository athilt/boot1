package com.hiltuprog.boot1.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="task")
@Getter
@Setter
public class Task {
	@Id
    @GeneratedValue
	private Long id;
	
	private String title;
	
    private LocalDate created;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Course> courses;
	
	//@OneToOne(cascade = CascadeType.ALL) 
	//private TaskExecution taskExecution;
}
