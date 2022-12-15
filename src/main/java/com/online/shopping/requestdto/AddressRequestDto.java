package com.online.shopping.requestdto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@NoArgsConstructor
public class AddressRequestDto {

    int id;

    @NotEmpty(message = "Enter street detail")
    String street;

    @NotEmpty(message = "Enter your city")
    String city;
    @NotEmpty(message = "Enter your state")
    String state;

    @NotEmpty(message = "Enter postal code")
    String postalCode;

    @NotEmpty(message = "Enter country")
    String country;

}
