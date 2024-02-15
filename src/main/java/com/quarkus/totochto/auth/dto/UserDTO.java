package com.quarkus.totochto.auth.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@RegisterForReflection
@ApplicationScoped
public class UserDTO {

	private Long userId;
	private String username;
	private String password;
	
}
