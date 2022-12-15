package com.online.shopping.requestdto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@NoArgsConstructor
public class ProductTypeRequestDto {

    private int id;

    @NotEmpty(message = "Enter Product type name")
    private String productType;

    @NotNull(message = "Enter Product subcategory id")
    @Positive(message = "Enter valid product subcategory id")
    private Integer subcategoryId;

}
