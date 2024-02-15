package com.quarkus.totochto.auth.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.jboss.logging.Logger;

import com.quarkus.totochto.auth.dto.ResponseDTO;
import com.quarkus.totochto.auth.dto.UserDTO;
import com.quarkus.totochto.auth.helper.EncrypterHelper;
import com.quarkus.totochto.auth.model.UserModel;
import com.quarkus.totochto.auth.repository.UserRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class UserService {

	@Inject
	UserRepository userRepository;
	
	@Inject
	ResponseDTO responseDTO;
	
	private final Logger log = Logger.getLogger(this.getClass());
	
	
	/**
	 * Get user info and validate his access
	 * @param dto {username, password}
	 * @return ResponseDTO
	 */
	public ResponseDTO getUser(UserDTO dto){
		EncrypterHelper helper = new EncrypterHelper();
		UserModel usr = userRepository.findByUsername(dto.getUsername());
		
		if(usr != null) {
			try {
				if(helper.validatePassword(dto.getPassword(), usr.getPassword())) {
					responseDTO.setCode(0);
					responseDTO.setMessage("Access Authorized");
				} else {
					responseDTO.setCode(-1);
					responseDTO.setMessage("Error - Access Unauthorized: Incorrect password");
				}
			} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
				log.error(e);
			}
		} else {
			responseDTO.setCode(-2);
			responseDTO.setMessage("Error - Access Unauthorized: User does not exist");
		}
		
		return responseDTO;
	}
	
	
	
}
