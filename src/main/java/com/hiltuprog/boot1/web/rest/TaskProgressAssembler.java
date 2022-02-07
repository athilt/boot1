package com.hiltuprog.boot1.web.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.core.Relation;
import org.springframework.stereotype.Component;

import com.hiltuprog.boot1.domain.TaskProgress;


@Component
public class TaskProgressAssembler implements RepresentationModelAssembler<TaskProgress, EntityModel<TaskProgress>> {
	@Override
	public EntityModel<TaskProgress> toModel(TaskProgress item) {
		EntityModel<TaskProgress> model = EntityModel.of(item,
				linkTo(methodOn(TaskProgressResource.class).one(item.getId())).withSelfRel(),
				linkTo(methodOn(TaskProgressResource.class).all()).withRel("taskprogresses"));	
		return model;
	}
}
