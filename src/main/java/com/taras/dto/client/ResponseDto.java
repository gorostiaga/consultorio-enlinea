package com.taras.dto.client;

import lombok.Data;

// this will be the response for both signup and signin operations 

// code: 
// 0 = the user is not registered please signup 
// 1 = user registered successfully please log in
// 2 = user logged in successfully 

@Data
public class ResponseDto {
	
    private String status;
    private String message;
    private int code;
    private String token; 
    
    
	public ResponseDto() {
		super();
	}
	public ResponseDto(String status, String message, int code) {
		super();
		this.status = status;
		this.message = message;
		this.code = code;
	}
	public ResponseDto(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	public ResponseDto(String status, String message, int code, String token) {
		super();
		this.status = status;
		this.message = message;
		this.code = code;
		this.token = token;
	} 
	
	
    
    

}
