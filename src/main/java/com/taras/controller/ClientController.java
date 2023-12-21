package com.taras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taras.dto.client.ResponseDto;
import com.taras.dto.client.SignInDto;
import com.taras.dto.client.SignupDto;
import com.taras.service.ClientService;

@RequestMapping("/client")
@RestController
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	
	@PostMapping("/signup")
    public ResponseDto signup(@RequestBody SignupDto signupDto) {
        return clientService.signUp(signupDto);
    }


    @PostMapping("/signin")
    public ResponseDto signIn(@RequestBody SignInDto signInDto) {
        return clientService.signIn(signInDto);
    }

}
