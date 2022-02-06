package com.hiltuprog.boot1.web.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.core.Relation;
import org.springframework.stereotype.Component;

import com.hiltuprog.boot1.domain.TaskProgress;
import com.hiltuprog.boot1.dto.CourseDTO;
import com.hiltuprog.boot1.dto.TaskProgressDTO;

@Component
public class TaskProgressAssembler implements RepresentationModelAssembler<TaskProgressDTO, EntityModel<TaskProgressDTO>> {
	@Override
	public EntityModel<TaskProgressDTO> toModel(TaskProgressDTO dto) {
		EntityModel<TaskProgressDTO> model = EntityModel.of(dto,
				linkTo(methodOn(TaskProgressResource.class).one(dto.getId())).withSelfRel(),
				linkTo(methodOn(TaskProgressResource.class).all()).withRel("taskprogresses"));	
		return model;
	}
}
