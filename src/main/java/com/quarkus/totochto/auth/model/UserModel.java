package com.quarkus.totochto.auth.model;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "c_user", schema = "config")
@Data
@RegisterForReflection
public class UserModel {

	@Id
	@GeneratedValue(generator="seqUser") 
	@SequenceGenerator(name="seqUser",sequenceName="c_user_usr_id_seq", allocationSize=1)
	@Column(name="usr_id")
	private Long usrId;
			
	private String username;
	
	private String password;
	
	@Column(name="branch_office_id")
	private Long branchOfficeId;
	
	private String status;
	
}
