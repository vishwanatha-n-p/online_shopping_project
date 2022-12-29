package com.online.shopping.servicelayer;

import com.online.shopping.requestdto.ProductCategoryRequestDto;
import com.online.shopping.responsedto.ProductCategoryResponseDto;
import com.online.shopping.services.ProductCategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductCategoryServiceTest {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Test
    public void test_addCategory() {
        ProductCategoryRequestDto productCategoryRequest = new ProductCategoryRequestDto("Other");
        ProductCategoryResponseDto productCategoryResponse = productCategoryService.addCategory(productCategoryRequest);
        assertEquals(productCategoryRequest.getCategoryName(), productCategoryResponse.getCategoryName());
    }

    @Test
    public void test_getAllCategory() {
        List<ProductCategoryResponseDto> productCategories = productCategoryService.getAllCategory();
        assertThat(productCategories.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void test_getSingleCategory() {
        int productCategoryId = 1;
        ProductCategoryResponseDto productCategoryResponse = productCategoryService.getSingleCategory(productCategoryId);
        assertEquals(productCategoryId, productCategoryResponse.getId());
    }

    @Test
    public void test_inactivateCategoryStatus() {
        int productCategoryId = 4;
        ProductCategoryResponseDto productCategoryResponse = productCategoryService.inactivateCategoryStatus(productCategoryId);
        assertEquals(productCategoryId, productCategoryResponse.getId());
    }

    @Test
    public void test_activateCategoryStatus() {
        int productCategoryId = 5;
        ProductCategoryResponseDto productCategoryResponse = productCategoryService.activateCategoryStatus(productCategoryId);
        assertEquals(productCategoryId, productCategoryResponse.getId());
    }

    @Test
    public void test_removeCategory() {
        int productCategoryId = 1;
        ProductCategoryResponseDto productCategory = productCategoryService.removeCategory(productCategoryId);
        assertEquals(productCategoryId, productCategory.getId());
    }

}
