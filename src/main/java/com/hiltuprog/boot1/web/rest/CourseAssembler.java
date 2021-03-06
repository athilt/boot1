package com.hiltuprog.boot1.web.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.hiltuprog.boot1.domain.Course;
import com.hiltuprog.boot1.domain.TaskProgress;
import com.hiltuprog.boot1.dto.CourseDTO;

@Component
public class CourseAssembler implements RepresentationModelAssembler<Course, EntityModel<Course>> {
	@Override
	public EntityModel<Course> toModel(Course item) {
		EntityModel<Course> model = EntityModel.of(item,
				linkTo(methodOn(CourseResource.class).one(item.getId())).withSelfRel(),
				linkTo(methodOn(CourseResource.class).all()).withRel("courses"));	
		return model;
	}
}
