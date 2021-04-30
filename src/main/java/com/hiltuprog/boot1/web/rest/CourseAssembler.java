package com.hiltuprog.boot1.web.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.core.Relation;
import org.springframework.stereotype.Component;
import com.hiltuprog.boot1.dto.CourseDTO;

@Component
public class TaskProgressAssembler implements RepresentationModelAssembler<TaskProgress, EntityModel<TaskProgress>> {
	@Override
	public EntityModel<TaskProgress> toModel(TaskProgress progress) {
	
		EntityModel<TaskProgress> taskProgressModel = EntityModel.of(progress,
				linkTo(methodOn(TaskProgress.class).one(progress.getId())).withSelfRel(),
				linkTo(methodOn(TaskProgress.class).all()).withRel("progress"));	
		return taskProgressModel;
	}
}
