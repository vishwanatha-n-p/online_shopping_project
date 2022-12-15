package com.online.shopping.responsedto;

import com.online.shopping.enums.ProductStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class ProductCategoryResponseDto {

    public int id;

    public String categoryName;

    private LocalDateTime updatedAt;

    ProductStatus status;

}
