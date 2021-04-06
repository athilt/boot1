package com.hiltuprog.boot1.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="curriculum")
@Getter
@Setter
public class Curriculum {
	@Id
    @GeneratedValue
	private Long id;
	
	private String title;
	
	private String description;
	
	@ManyToMany
	//@JoinTable(
	//		  name = "curriculum_course", 
	//		  joinColumns = @JoinColumn(name = "curriculum_id"), 
	//		  inverseJoinColumns = @JoinColumn(name = "course_id"))
	@OrderBy("id")
	private List<Course> courses;

}