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

    public HighlightsRequestDto(String modelNumber, String features, String size) {
        this.modelNumber = modelNumber;
        this.features = features;
        this.size = size;
    }

}
