package com.online.shopping.requestdto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@NoArgsConstructor
public class CurrencyRequestDto {

    int id;

    @NotEmpty(message = "Enter Country")
    String country;

    @NotEmpty(message = "Enter currency code")
    String currencyCode;

}
