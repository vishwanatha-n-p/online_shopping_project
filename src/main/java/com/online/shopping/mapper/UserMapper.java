package com.online.shopping.mapper;

import com.online.shopping.entity.User;
import com.online.shopping.requestdto.UserRequestDto;
import com.online.shopping.responsedto.UserResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    public UserResponseDto convertEntityToDto(User user) {
        return mapper.map(user, UserResponseDto.class);
    }

    public User convertDtoToEntity(UserRequestDto userRequestDto) {
        return new User(userRequestDto.getUserName(), bcryptEncoder.encode(userRequestDto.getPassword()));
    }

}
