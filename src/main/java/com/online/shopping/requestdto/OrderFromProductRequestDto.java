package com.online.shopping.requestdto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderFromProductRequestDto {

    int id;

    @NotNull(message = "Enter product id")
    @Positive(message = "Enter valid product id")
    Integer productId;

    @Min(value = 1, message = "Enter quantity greater than or equal to 1")
    int quantity;

}
