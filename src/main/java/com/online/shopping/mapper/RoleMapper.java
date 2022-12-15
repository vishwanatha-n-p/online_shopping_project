package com.online.shopping.mapper;

import com.online.shopping.entity.Role;
import com.online.shopping.requestdto.RoleRequestDto;
import com.online.shopping.responsedto.RoleResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    @Autowired
    private ModelMapper mapper;

    public Role convertDtoToEntity(RoleRequestDto roleRequestDto) {
        return mapper.map(roleRequestDto, Role.class);
    }

    public RoleResponseDto convertEntityToDto(Role role) {
        return mapper.map(role, RoleResponseDto.class);
    }

}
