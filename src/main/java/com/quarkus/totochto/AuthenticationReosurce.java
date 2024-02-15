package com.quarkus.totochto;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api/authentications")
@OpenAPIDefinition(
		info = @Info(
				title = "Auth Service",
				description = "Authentication service application for SANET 2.0 [sanet-ms-auth]",
				version = "1.0.0"
				)
)
public class AuthenticationReosurce extends Application{

}
