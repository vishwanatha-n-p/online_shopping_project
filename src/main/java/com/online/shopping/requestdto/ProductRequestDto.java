package com.online.shopping.requestdto;

import com.online.shopping.entity.Highlights;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Setter
@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequestDto {

    int id;

    @NotEmpty(message = "Enter Product name")
    String productName;

    @NotEmpty(message = "Enter product type id")
    @Positive(message = "Enter valid product type id")
    int productTypeId;

    @NotEmpty(message = "Enter product color")
    String color;

    @NotEmpty(message = "Enter highlights")
    Highlights highlights;

    @Min(value = 1, message = "Enter product count")
    Integer productCount;

}
