package com.online.shopping.responsedto;

import com.online.shopping.entity.Highlights;
import com.online.shopping.entity.ProductType;
import com.online.shopping.entity.Seller;
import com.online.shopping.enums.ProductStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class ProductResponseDto {

    int id;

    String productName;

    ProductType productType;

    String color;

    Highlights highlights;

    List<Seller> sellers;

    ProductStatus status;

    int productCount;

}
