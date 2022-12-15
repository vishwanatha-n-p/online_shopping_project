package com.online.shopping.responsedto;

import com.online.shopping.entity.Product;
import com.online.shopping.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class WishListResponseDto {

    int id;

    User user;

    List<Product> products;

}
