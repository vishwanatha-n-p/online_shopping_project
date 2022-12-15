package com.online.shopping.services;

import com.online.shopping.constants.ErrorConstants;
import com.online.shopping.exception.GeneralException;
import com.online.shopping.mapper.RoleMapper;
import com.online.shopping.repository.RoleRepository;
import com.online.shopping.requestdto.RoleRequestDto;
import com.online.shopping.responsedto.RoleResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleMapper roleMapper;

    public List<RoleResponseDto> getAllRoles() {
        return roleRepository.findAll().stream().map(role -> roleMapper.convertEntityToDto(role)).collect(Collectors.toList());
    }

    public RoleResponseDto getSingleRole(int roleId) {
        return roleMapper.convertEntityToDto(roleRepository.findById(roleId).orElseThrow(() -> new GeneralException(ErrorConstants.ROLE_NOT_FOUND_ERROR + roleId)));
    }

    public RoleResponseDto addRole(RoleRequestDto roleRequestDto) {
        return roleMapper.convertEntityToDto(roleRepository.save(roleMapper.convertDtoToEntity(roleRequestDto)));
    }

    public String removeRole(int roleId) {
        roleRepository.deleteById(roleId);
        return "Successfully deleted Role where Role id:" + roleId;
    }

}
