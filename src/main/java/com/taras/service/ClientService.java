package com.taras.service;

import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taras.dto.client.ResponseDto;
import com.taras.dto.client.SignInDto;
import com.taras.dto.client.SignupDto;
import com.taras.exceptions.CustomException;

import com.taras.model.Client;
import com.taras.model.ClientToken;
import com.taras.repository.ClientRepo;


@Service
public class ClientService {
	
	@Autowired
	private ClientRepo clientRepo;
	

	
	@Autowired
	private ClientTokenService clientTokenService;
	

	private ClientToken clientToken; 
	
 
	
	@Transactional
    public ResponseDto signUp(SignupDto signupDto) {
        // check if user is already present
        if (Objects.nonNull(clientRepo.findByPhone(signupDto.getPhone()))) {
            // we have an user
            throw new CustomException("user already present");
        }

        Client client = new Client(signupDto.getFirstName(), signupDto.getLastName(),
                signupDto.getEmail(), signupDto.getPhone());

        //save the client
        clientRepo.save(client);

        // create the token

        final ClientToken clientToken = new ClientToken(client);

        clientTokenService.saveConfirmationToken(clientToken);

        ResponseDto responseDto = new ResponseDto("success", "user created succesfully", 1);
        return responseDto;
    }

    public ResponseDto signIn(SignInDto signInDto) {
        // find user by email

        Client client = clientRepo.findByPhone(signInDto.getPhone());
        
        if (Objects.isNull(client)) {
            //throw new AuthenticationFailException("user is not valid");
        	// if the user is not found 
        	return new ResponseDto("no user", "Por favor introduce los siguientes datos", 0, "null"); 
        } else {
        	clientToken = clientTokenService.getToken(client);        	 
        	 return new ResponseDto("user logged in", "Hola "+client.getFirstName(), 2, clientToken.getToken()); 
		}
    }
    
    public Client getUserByPhone(int phone) {
    	return clientRepo.findByPhone(phone); 
    }


}
