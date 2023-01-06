package com.online.shopping.unittestcases.services;

import com.online.shopping.requestdto.UserRequestDto;
import com.online.shopping.responsedto.UserResponseDto;
import com.online.shopping.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void test_addUser() {
        UserRequestDto userRequest = new UserRequestDto("Ramadas", "Ramadas123", "Customer");
        UserResponseDto userResponse = userService.addUser(userRequest);
        assertEquals(userRequest.getUserName(), userResponse.getUserName());
        assertEquals(userRequest.getRoleName(), userResponse.getRole());
    }

    @Test
    public void test_getAllUser() {
        List<UserResponseDto> users = userService.getAllUser();
        assertThat(users.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void test_getSingleUser() {
        int userId = 1;
        UserResponseDto userResponse = userService.getSingleUser(userId);
        assertEquals(userId, userResponse.getId());
    }

    @Test
    public void test_removeUser() {
        int userId = 11;
        UserResponseDto userResponse = userService.removeUser(userId);
        assertEquals(userId, userResponse.getId());
    }

}
