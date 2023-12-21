package com.taras.dto.client;

import lombok.Data;

@Data
public class SignInDto {
	
	private int phone;

	public SignInDto() {
		super();
	}

	public SignInDto(int phone) {
		super();
		this.phone = phone;
	}
	
	

}
