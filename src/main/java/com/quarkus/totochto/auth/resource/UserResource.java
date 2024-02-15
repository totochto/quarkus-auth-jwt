package com.quarkus.totochto.auth.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import com.quarkus.totochto.auth.dto.ResponseDTO;
import com.quarkus.totochto.auth.dto.UserDTO;
import com.quarkus.totochto.auth.service.UserService;


@Path("/users")
@ApplicationScoped
public class UserResource {

	@Inject
	UserService userService;
	
	
	@Path("/access")
	@POST
	@RolesAllowed({"admin","user"})
	@Produces
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(
			operationId = "getUserByPwd",
			summary = "Validates user access",
			description = "Validates users access to API"
	)
	@APIResponse(
			responseCode = "200",
			description = "OK",
			content = @Content(mediaType = MediaType.APPLICATION_JSON)
	)
	public Response getUserByPwd(UserDTO dto) {
		ResponseDTO resp = userService.getUser(dto);
		return Response.ok(resp).build();
	}
		
		
}
