package com.online.shopping.errormodel;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
public class ErrorModel {

    HttpStatus httpStatus;

    @CreationTimestamp
    LocalDateTime localDateTime;

    String message;

    String details;

}
