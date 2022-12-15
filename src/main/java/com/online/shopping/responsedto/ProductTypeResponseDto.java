package com.online.shopping.responsedto;

import com.online.shopping.entity.ProductSubcategory;
import com.online.shopping.enums.ProductStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ProductTypeResponseDto {

    private int id;

    private String productType;

    private ProductSubcategory productSubcategory;

    ProductStatus status;

}
