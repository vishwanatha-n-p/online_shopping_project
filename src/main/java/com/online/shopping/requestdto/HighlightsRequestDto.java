package com.online.shopping.requestdto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HighlightsRequestDto {

    int id;

    @NotEmpty(message = "Enter model number")
    String modelNumber;

    @NotEmpty(message = "Enter product features")
    String features;

    @NotEmpty(message = "Enter product size")
    String size;

}
