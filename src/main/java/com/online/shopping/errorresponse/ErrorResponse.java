package com.online.shopping.errorresponse;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ErrorResponse {

    int status;

    String message;

    @UpdateTimestamp
    LocalDateTime localDateTime;

}
