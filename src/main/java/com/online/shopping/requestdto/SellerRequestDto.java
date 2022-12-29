package com.online.shopping.requestdto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SellerRequestDto {

    int id;

    @NotEmpty(message = "Enter Seller name")
    String sellerName;

    @NotEmpty(message = "Enter seller service")
    String service;

    @NotEmpty(message = "Enter phone number including country code")
    @Length(min = 12, max = 12, message = "Enter 12 number contact includes country code")
    String contactNumber;

    @NotEmpty(message = "Enter email address")
    @Email(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
            message = "Enter well-formed email address")
    String email;

    @NotEmpty(message = "Enter your city")
    String city;

    @NotEmpty(message = "Enter your state")
    String state;

    @NotEmpty(message = "Enter your postal code")
    String postalCode;

    @NotEmpty(message = "Enter your country")
    String country;

    public SellerRequestDto(String sellerName, String service, String contactNumber, String email, String city, String state, String postalCode, String country) {
        this.sellerName = sellerName;
        this.service = service;
        this.contactNumber = contactNumber;
        this.email = email;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
    }

}
