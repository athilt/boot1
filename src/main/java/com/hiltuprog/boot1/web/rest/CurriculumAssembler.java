package com.hiltuprog.boot1.web.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.hiltuprog.boot1.domain.Course;
import com.hiltuprog.boot1.domain.Curriculum;
import com.hiltuprog.boot1.domain.TaskProgress;
import com.hiltuprog.boot1.dto.CourseDTO;

@Component
public class CurriculumAssembler implements RepresentationModelAssembler<Curriculum, EntityModel<Curriculum>> {
	@Override
	public EntityModel<Curriculum> toModel(Curriculum item) {
		EntityModel<Curriculum> model = EntityModel.of(item,
				linkTo(methodOn(CourseResource.class).one(item.getId())).withSelfRel(),
				linkTo(methodOn(CourseResource.class).all()).withRel("curriculums"));	
		return model;
	}
}
