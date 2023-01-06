package com.online.shopping.utility;

import com.online.shopping.jwtcontrollers.JwtAuthenticationController;
import com.online.shopping.jwtmodel.JwtResponse;
import com.online.shopping.requestdto.UserRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class HeadersCreation {

    @Autowired
    private JwtAuthenticationController jwtAuthenticationController;

    public HttpHeaders obtainHeaders(UserRequestDto userRequest) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", getAuthenticationToken(userRequest));
        return headers;
    }

    private String getAuthenticationToken(UserRequestDto userRequest) throws Exception {
        ResponseEntity<?> responseEntity = jwtAuthenticationController.authentication(userRequest);
        JwtResponse response = (JwtResponse) responseEntity.getBody();
        return "Bearer " + response.getJwtToken();
    }

}
