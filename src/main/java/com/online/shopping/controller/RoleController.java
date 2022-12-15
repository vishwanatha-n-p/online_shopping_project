package com.online.shopping.controller;

import com.online.shopping.requestdto.RoleRequestDto;
import com.online.shopping.responsedto.RoleResponseDto;
import com.online.shopping.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PreAuthorize("hasRole('ROLE_Manager')")
    @GetMapping
    public List<RoleResponseDto> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/{roleId}")
    public RoleResponseDto getSingleRole(@PathVariable int roleId) {
        return roleService.getSingleRole(roleId);
    }

    @PostMapping
    public RoleResponseDto addRole(@Valid @RequestBody RoleRequestDto roleRequestDto) {
        return roleService.addRole(roleRequestDto);
    }

    @PreAuthorize("hasRole('ROLE_Manager')")
    @DeleteMapping("/{roleId}")
    public String removeRole(@PathVariable int roleId) {
        return roleService.removeRole(roleId);
    }

}
