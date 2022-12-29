package com.online.shopping.requestdto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PriceDetailRequestDto {

    int id;

    @NotNull(message = "Enter product id")
    @Positive(message = "Enter valid product id")
    Integer productId;

    @NotEmpty(message = "Enter Seller name")
    String sellerName;

    @NotNull(message = "Enter Currency id")
    @Positive
    Integer currencyId;

    @NotNull(message = "Enter product price")
    Long price;

    @Min(value = 1, message = "Enter products count")
    int availabilityCount;

    String discount;

    int specialOfferDiscount;

    int deliveryCharge;

    @NotNull(message = "Enter final price")
    @Positive
    Long finalPrice;

    public PriceDetailRequestDto(Integer productId, String sellerName, Integer currencyId, Long price, int availabilityCount, String discount,
                                 int specialOfferDiscount, int deliveryCharge, Long finalPrice) {
        this.productId = productId;
        this.sellerName = sellerName;
        this.currencyId = currencyId;
        this.price = price;
        this.availabilityCount = availabilityCount;
        this.discount = discount;
        this.specialOfferDiscount = specialOfferDiscount;
        this.deliveryCharge = deliveryCharge;
        this.finalPrice = finalPrice;
    }
}
