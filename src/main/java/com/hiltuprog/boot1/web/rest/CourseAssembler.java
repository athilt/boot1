package com.hiltuprog.boot1.web.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.core.Relation;
import org.springframework.stereotype.Component;
import com.hiltuprog.boot1.dto.CourseDTO;

@Component
@Relation(collectionRelation = "courses", itemRelation = "course")
public class CourseAssembler implements RepresentationModelAssembler<CourseDTO, EntityModel<CourseDTO>> {
	@Override
	public EntityModel<CourseDTO> toModel(CourseDTO course) {
		EntityModel<CourseDTO> courseModel = EntityModel.of(course, //
				linkTo(methodOn(CourseResource.class).one(course.getId())).withSelfRel(),
				linkTo(methodOn(CourseResource.class).all()).withRel("courses"));
		
		return courseModel;
	}
}
