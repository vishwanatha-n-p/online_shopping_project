package com.online.shopping.unittestcases.services;

import com.online.shopping.requestdto.ProductSubcategoryRequestDto;
import com.online.shopping.responsedto.ProductSubcategoryResponseDto;
import com.online.shopping.services.ProductSubcategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductSubcategoryServiceTest {

    @Autowired
    private ProductSubcategoryService productSubcategoryService;

    @Test
    public void test_addSubcategory() {
        ProductSubcategoryRequestDto subcategoryRequestDto = new ProductSubcategoryRequestDto("Kitchen, Cookware & Serveware", 3);
        ProductSubcategoryResponseDto subcategoryResponse = productSubcategoryService.addSubcategory(subcategoryRequestDto);
        assertEquals(subcategoryRequestDto.getSubcategoryName(), subcategoryResponse.getSubcategoryName());
        assertEquals(subcategoryRequestDto.getCategoryId(), subcategoryResponse.getProductCategory().getId());
    }

    @Test
    public void test_getAllSubcategory() {
        List<ProductSubcategoryResponseDto> productSubcategories = productSubcategoryService.getAllSubcategory();
        assertThat(productSubcategories.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void test_getSingleSubcategory() {
        int subcategoryId = 1;
        ProductSubcategoryResponseDto productSubcategoryResponse = productSubcategoryService.getSingleSubcategory(subcategoryId);
        assertEquals(subcategoryId, productSubcategoryResponse.getId());
    }

    @Test
    public void test_inactivateSubcategoryStatus() {
        int subcategoryId = 5;
        ProductSubcategoryResponseDto productSubcategoryResponse = productSubcategoryService.inactivateSubcategoryStatus(subcategoryId);
        assertEquals(subcategoryId, productSubcategoryResponse.getId());
    }

    @Test
    public void test_activateSubcategoryStatus() {
        int subcategoryId = 2;
        ProductSubcategoryResponseDto productSubcategoryResponse = productSubcategoryService.activateSubcategoryStatus(subcategoryId);
        assertEquals(subcategoryId, productSubcategoryResponse.getId());
    }

    @Test
    public void test_getParticularCategorySubcategories() {
        int categoryId = 1;
        List<ProductSubcategoryResponseDto> productSubcategories = productSubcategoryService.getSubcategoriesOfCategory(categoryId);
        assertThat(productSubcategories.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void test_removeSubcategory() {
        int subcategoryId = 6;
        ProductSubcategoryResponseDto productSubcategory = productSubcategoryService.removeSubcategory(subcategoryId);
        assertEquals(subcategoryId, productSubcategory.getId());
    }

}
