package com.quarkus.totochto.jwt.resource;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import com.quarkus.totochto.jwt.service.TokenService;


@Path("/tokens")
@ApplicationScoped
public class TokenResource {
	
	@Inject
    TokenService tokenService;
	
	@GET
	@Path("/jwts")
	@Produces
	@Operation(
			operationId = "generateToken",
			summary = "Generates token",
			description = "Generates a jwt token"
	)
	@APIResponse(
			responseCode = "200",
			description = "OK",
			content = @Content(mediaType = MediaType.APPLICATION_JSON)
	)
	public Response generateToken() {
		String jwt = tokenService.generateToken();
		return Response.ok(jwt).build();
	}
	
}
