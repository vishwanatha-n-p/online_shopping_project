package com.online.shopping.services;

import com.online.shopping.constants.ErrorConstants;
import com.online.shopping.entity.Role;
import com.online.shopping.entity.User;
import com.online.shopping.exception.RoleNotFoundException;
import com.online.shopping.exception.UserNotFoundException;
import com.online.shopping.mapper.UserMapper;
import com.online.shopping.repository.RoleRepository;
import com.online.shopping.repository.UserRepository;
import com.online.shopping.requestdto.UserRequestDto;
import com.online.shopping.responsedto.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserMapper userMapper;

    public List<UserResponseDto> getAllUser() {
        return userRepository.findAll().stream().map(user -> userMapper.convertEntityToDto(user)).collect(Collectors.toList());
    }

    public UserResponseDto getSingleUser(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(ErrorConstants.USER_NOT_FOUND_ERROR + userId));
        return userMapper.convertEntityToDto(user);
    }

    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        if (validateUser(userRequestDto.getId())) {
            throw new UserNotFoundException(ErrorConstants.USER_EXIST_ERROR);
        }
        Role role = roleRepository.findByRoleName(userRequestDto.getRoleName()).orElseThrow(() -> new RoleNotFoundException(ErrorConstants.ROLE_NOT_EXIST_ERROR));
        User userResponse = userMapper.convertDtoToEntity(userRequestDto);
        userResponse.setRole(role);
        return userMapper.convertEntityToDto(userRepository.save(userResponse));
    }

    private boolean validateUser(int id) {
        return userRepository.existsById(id);
    }

    public String removeUser(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(ErrorConstants.USER_NOT_FOUND_ERROR + userId));
        userRepository.delete(user);
        return "Successfully deleted the User where User id:" + userId;
    }

}
