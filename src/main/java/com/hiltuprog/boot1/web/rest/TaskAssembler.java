package com.hiltuprog.boot1.web.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.hiltuprog.boot1.dto.TaskDTO;
import com.hiltuprog.boot1.dto.UserDTO;

@Component
public class TaskAssembler implements RepresentationModelAssembler<TaskDTO, EntityModel<TaskDTO>> {
	@Override
	public EntityModel<TaskDTO> toModel(TaskDTO task) {
		EntityModel<TaskDTO> taskModel = EntityModel.of(task,
				//linkTo(methodOn(UserResource.class).one(((com.hiltuprog.boot1.dto.UserDTO) user).getId())).withSelfRel(),
				linkTo(methodOn(UserResource.class).one(task.getId())).withSelfRel(),
				linkTo(methodOn(UserResource.class).all()).withRel("tasks"));
		return taskModel;
	}
}
