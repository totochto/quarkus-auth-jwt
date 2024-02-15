package com.quarkus.totochto.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.quarkus.totochto.auth.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Integer>{
	
	@Query("select m from UserModel m where m.username = ?1")
	UserModel findByUsername(String username);
	
	List<UserModel> findAll();
	
}
