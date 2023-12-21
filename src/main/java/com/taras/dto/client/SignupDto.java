package com.taras.dto.client;

import lombok.Data;

@Data
public class SignupDto {
	
	private String firstName;
    private String lastName;
    private String email;
    private int phone;
	
    public SignupDto() {
		super();
	}
	public SignupDto(String firstName, String lastName, String email, int phone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
	}
	
    

}
