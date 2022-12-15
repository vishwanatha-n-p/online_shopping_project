package com.online.shopping.responsedto;

import com.online.shopping.entity.Currency;
import com.online.shopping.entity.Product;
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
public class PriceDetailResponseDto {

    int id;

    Product product;

    String sellerName;

    Currency currency;

    long price;

    int productCount;

    String discount;

    int specialOfferDiscount;

    int deliveryCharge;

    long finalPrice;

    LocalDateTime updatedAt;

}
