package com.justmehr.backend.domain.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserDTO {
	
    Long id;	
	String firstName;
	String lastName;	
	String userName;
	String password;
	String email;
	String address;	
	String phone;

}
