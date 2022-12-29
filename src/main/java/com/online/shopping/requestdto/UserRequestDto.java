package com.online.shopping.requestdto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@NoArgsConstructor
public class UserRequestDto {

    private int id;

    @NotEmpty(message = "Enter user name")
    private String userName;

    @NotEmpty(message = "Enter password")
    private String password;

    @NotEmpty(message = "Enter role")
    private String roleName;

    public UserRequestDto(String userName, String password, String roleName) {
        this.userName = userName;
        this.password = password;
        this.roleName = roleName;
    }
}
