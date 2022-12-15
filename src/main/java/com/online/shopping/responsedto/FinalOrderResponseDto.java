package com.online.shopping.responsedto;

import com.online.shopping.entity.Payment;
import com.online.shopping.entity.Product;
import com.online.shopping.enums.OrderStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FinalOrderResponseDto {

    int id;

    Product product;

    int quantity;

    long cost;

    OrderStatus orderStatus;

    LocalDateTime orderDate;

    LocalDate shipDate;

    LocalDate deliveryDate;

    Payment payment;

}
