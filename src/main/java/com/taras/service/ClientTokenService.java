package com.taras.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taras.exceptions.AuthenticationFailException;
import com.taras.model.Client;
import com.taras.model.ClientToken;
import com.taras.repository.ClientTokenRepo;

@Service
public class ClientTokenService {
	
	@Autowired
	private ClientTokenRepo clientTokenRepo;
	
	public void saveConfirmationToken(ClientToken clientToken) {
		clientTokenRepo.save(clientToken);
    }

    public ClientToken getToken(Client client) {
        return clientTokenRepo.findByClient(client);
    }


    public Client getUser(String token) {
        final ClientToken clientToken = clientTokenRepo.findByToken(token);
        if(Objects.isNull(clientToken)) {
            return null;
        }
        // authenticationToken is not null
        return clientToken.getClient();
    }

    public void authenticate(String token) throws AuthenticationFailException {
        // null check
        if(Objects.isNull(token)) {
            // throw an exception
            throw new AuthenticationFailException("token not present");
        }
        if(Objects.isNull(getUser(token))) {
            throw new AuthenticationFailException("token not valid");
        }
    }

}
