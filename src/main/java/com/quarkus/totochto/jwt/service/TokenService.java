package com.quarkus.totochto.jwt.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Singleton;

@Singleton
public class TokenService {

	/**
	 * Generates a jwt token
	 * @return jwt token string
	 */
	public String generateToken() {
		Set<String> roles = new HashSet<>(
				Arrays.asList("admin","user")
		);
		
		long duration = System.currentTimeMillis() + 3600;
		
		return Jwt.issuer("jwt")
		.subject("auth")
		.groups(roles)
		.expiresAt(duration)
		.sign();
		
	}
	
}
