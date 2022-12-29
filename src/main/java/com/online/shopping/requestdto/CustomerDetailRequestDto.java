package com.online.shopping.requestdto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@NoArgsConstructor
public class CustomerDetailRequestDto {

    private int id;

    @NotEmpty(message = "Enter first name")
    private String firstName;

    @NotEmpty(message = "Enter last name")
    private String lastName;

    @NotEmpty(message = "Enter email address")
    @Email(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
            message = "Enter well-formed email address")
    private String email;

    @NotEmpty(message = "Enter phone number including country code")
    @Length(min = 12, max = 12, message = "Enter 12 number contact includes country code")
    private String contactNumber;

    public CustomerDetailRequestDto(String firstName, String lastName, String email, String contactNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNumber = contactNumber;
    }

}
