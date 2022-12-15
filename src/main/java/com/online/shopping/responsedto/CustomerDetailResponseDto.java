package com.online.shopping.responsedto;

import com.online.shopping.entity.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CustomerDetailResponseDto {

    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String contactNumber;

    private Address address;

}
