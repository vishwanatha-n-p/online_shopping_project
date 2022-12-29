package com.online.shopping.responsedto;

import com.online.shopping.entity.User;
import com.online.shopping.enums.OrderStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MyOrderResponseDto {

    int id;

    User user;

    String productName;

    String color;

    String modelNumber;

    int quantity;

    long totalPrice;

    OrderStatus orderStatus;

    LocalDateTime orderDate;

    LocalDateTime updatedAt;

}
