package com.hiltuprog.boot1.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="course")
@Getter
@Setter
@Relation(collectionRelation="courses")
public class Course {
	@Id
    @GeneratedValue
	private Long id;

	private String title;

	private String description;
	
	//private String foo;
	
	//@JsonIgnore
	//@ManyToMany
	/*
	@OrderBy("id")
	private List<Curriculum> curriculums;
	*/
	
	@ManyToMany(fetch = FetchType.LAZY)
	@OrderBy("id")
	private  List<User> users;
	
	@JsonIgnore
	/*
	@ManyToMany
	@JoinTable(
  		  name = "task_courses", 
  		  joinColumns = @JoinColumn(name = "courses_id"), 
  		  inverseJoinColumns = @JoinColumn(name = "task_id"))
  		  */
	@OneToMany(fetch = FetchType.LAZY)
	@OrderBy("id")
	private  List<Task> tasks;
	
	@Override
    public String toString()
    {
    	return ("title: " + title);
    }
}
