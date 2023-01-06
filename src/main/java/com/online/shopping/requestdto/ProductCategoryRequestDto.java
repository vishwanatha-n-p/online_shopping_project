package com.online.shopping.requestdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryRequestDto {

    private int id;

    @NotNull(message = "Enter Category name")
    private String categoryName;

    public ProductCategoryRequestDto(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "ProductCategoryRequestDto{" + "id=" + id + ", categoryName='" + categoryName + '\'' + '}';
    }
}
