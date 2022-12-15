package com.online.shopping.responsedto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class AddressResponseDto {

    int id;

    String street;

    String city;

    String state;

    String postalCode;

    String country;

    LocalDateTime updatedAt;

}
