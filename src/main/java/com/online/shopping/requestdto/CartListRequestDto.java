package com.online.shopping.requestdto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartListRequestDto {

    int id;

    @Positive(message = "Enter valid Product id")
    @NotNull(message = "Enter Product id")
    Integer productId;

}
