package com.online.shopping.responsedto;

import com.online.shopping.entity.ProductCategory;
import com.online.shopping.enums.ProductStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class ProductSubcategoryResponseDto {

    public int id;

    public String subcategoryName;

    private LocalDateTime updatedAt;

    private ProductCategory productCategory;

    ProductStatus status;

}
