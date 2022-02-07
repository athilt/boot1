package com.hiltuprog.boot1.web.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.hiltuprog.boot1.domain.Task;


@Component
public class TaskAssembler implements RepresentationModelAssembler<Task, EntityModel<Task>> {
	@Override
	public EntityModel<Task> toModel(Task item) {
		EntityModel<Task> taskModel = EntityModel.of(item,
				//linkTo(methodOn(UserResource.class).one(((com.hiltuprog.boot1.dto.UserDTO) user).getId())).withSelfRel(),
				linkTo(methodOn(UserResource.class).one(item.getId())).withSelfRel(),
				linkTo(methodOn(UserResource.class).all()).withRel("tasks"));
		return taskModel;
	}
}
