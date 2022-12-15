package com.online.shopping.responsedto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserResponseDto {

    private int id;

    private String userName;

    private String password;

    private String role;

}
