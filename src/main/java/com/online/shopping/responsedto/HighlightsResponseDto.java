package com.online.shopping.responsedto;

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
public class HighlightsResponseDto {

    int id;

    String productName;

    String features;

    String size;

    LocalDateTime updatedAt;

}
