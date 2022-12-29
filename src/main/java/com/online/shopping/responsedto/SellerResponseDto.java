package com.online.shopping.responsedto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SellerResponseDto {

    int id;

    String sellerName;

    String service;

    String contactNumber;

    String email;

    String city;

    String state;

    String postalCode;

    String country;

    LocalDateTime updatedAt;

}
