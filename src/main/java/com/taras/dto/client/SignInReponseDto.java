package com.taras.dto.client;

import lombok.Data;

@Data
public class SignInReponseDto {
	
	private String status;
    private String token;
	public SignInReponseDto() {
		super();
	}
	public SignInReponseDto(String status, String token) {
		super();
		this.status = status;
		this.token = token;
	}
    
    

}
