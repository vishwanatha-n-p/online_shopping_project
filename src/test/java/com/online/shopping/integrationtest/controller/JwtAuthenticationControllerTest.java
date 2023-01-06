package com.online.shopping.integrationtest.controller;

import com.online.shopping.OnlineShoppingApplication;
import com.online.shopping.jwtmodel.JwtResponse;
import com.online.shopping.requestdto.UserRequestDto;
import com.online.shopping.responsedto.UserResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = OnlineShoppingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JwtAuthenticationControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    String localHost = "http://localhost:";

    @Test
    public void testRegisterUser() {
        UserRequestDto userRequest = new UserRequestDto("Abhilash", "Abhilash123", "Customer");
        String urlToRegisterUser = localHost + port + "/register";
        UserResponseDto userResponse = testRestTemplate.postForObject(urlToRegisterUser, userRequest, UserResponseDto.class);
        System.out.println("+++++++++++++++++++++++++");
        System.out.println(userResponse);
        System.out.println("+++++++++++++++++++++++++");
        assertNotNull(userResponse);
    }

    @Test
    public void testAuthentication() {
        UserRequestDto userRequest = new UserRequestDto("Ramadas", "Ramadas123", "Customer");
        String urlToAuthentication = localHost + port + "/authenticate";
//        HttpHeaders headers = new HttpHeaders();
        //HttpEntity<UserRequestDto> entity = new HttpEntity<>(userRequest, headers);

        ResponseEntity<?> responseEntity = testRestTemplate.postForObject(urlToAuthentication, userRequest, ResponseEntity.class);
        //ResponseEntity<JwtResponse> responseEntity = testRestTemplate.postForObject(urlToAuthentication, userRequest, ResponseEntity.class);
        JwtResponse response = (JwtResponse) responseEntity.getBody();
        //JwtResponse response = responseEntity.getBody();
        assertNotNull(response.getJwtToken());

        System.out.println("+++++++++++++++++++++++++");
        System.out.println(response);
        System.out.println(response.getJwtToken());
        System.out.println("+++++++++++++++++++++++++");


    }

}
