package com.online.shopping.responsedto;

import com.online.shopping.entity.Product;
import com.online.shopping.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartListResponseDto {

    int id;

    User user;

    List<Product> products;

}
