package com.hiltuprog.boot1.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="task_progress")
@Getter
@Setter
public class TaskProgress {
	@Id
    @GeneratedValue
	private Long id;
	
	private String title;
	
	private String content;
	
    private LocalDate created;
}
