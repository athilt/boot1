package com.hiltuprog.boot1.web.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.hiltuprog.boot1.dto.UserDTO;

@Component
public class UserAssembler implements RepresentationModelAssembler<UserDTO, EntityModel<UserDTO>> {
	@Override
	public EntityModel<UserDTO> toModel(UserDTO user) {
		EntityModel<UserDTO> userModel = EntityModel.of(user,
				linkTo(methodOn(UserResource.class).one(((com.hiltuprog.boot1.dto.UserDTO) user).getId())).withSelfRel(),
				linkTo(methodOn(UserResource.class).all()).withRel("users"));
		
		return userModel;
	}
}
