package com.online.shopping.servicelayer;

import com.online.shopping.requestdto.RoleRequestDto;
import com.online.shopping.responsedto.RoleResponseDto;
import com.online.shopping.services.RoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RoleServiceTest {

    @Autowired
    private RoleService roleService;

    @Test
    public void test_addRole() {
        RoleRequestDto roleRequest = new RoleRequestDto("Other");
        RoleResponseDto roleResponse = roleService.addRole(roleRequest);
        assertEquals(roleRequest.getRoleName(), roleResponse.getRoleName());
    }

    @Test
    public void test_getAllRoles() {
        List<RoleResponseDto> roles = roleService.getAllRoles();
        assertThat(roles.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void test_getSingleRole() {
        int roleId = 2;
        RoleResponseDto roleResponse = roleService.getSingleRole(roleId);
        assertEquals(roleId, roleResponse.getId());
    }

    @Test
    public void test_removeRole() {
        int roleId = 4;
        RoleResponseDto role = roleService.removeRole(roleId);
        assertThat(role).isNotNull();
    }

}
