package com.online.shopping.requestdto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@NoArgsConstructor
public class WishListRequestDto {

    int id;

    @NotNull(message = "Enter product id")
    @Positive(message = "Enter valid product id")
    Integer productId;

    public WishListRequestDto(Integer productId) {
        this.productId = productId;
    }

}
