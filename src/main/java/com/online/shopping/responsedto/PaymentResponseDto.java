package com.online.shopping.responsedto;

import com.online.shopping.entity.CustomerDetail;
import com.online.shopping.entity.PaymentMode;
import com.online.shopping.enums.PaymentStatus;
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
public class PaymentResponseDto {

    int id;

    PaymentMode paymentMode;

    Long amount;

    LocalDateTime paymentDate;

    PaymentStatus paymentStatus;

    CustomerDetail customerDetail;

}
