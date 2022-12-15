package com.online.shopping.exception;

public class ProductTypeNotFoundException extends RuntimeException {

    public ProductTypeNotFoundException(String message) {
        super(message);
    }

}
