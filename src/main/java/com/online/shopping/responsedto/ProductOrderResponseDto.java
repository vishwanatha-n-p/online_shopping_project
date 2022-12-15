package com.online.shopping.responsedto;

import com.online.shopping.entity.Product;
import com.online.shopping.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductOrderResponseDto {

    int id;

    Product product;

    int quantity;

    long cost;

    User user;

}

