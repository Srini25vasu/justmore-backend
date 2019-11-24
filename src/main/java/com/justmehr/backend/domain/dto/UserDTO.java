package com.justmehr.backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
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
