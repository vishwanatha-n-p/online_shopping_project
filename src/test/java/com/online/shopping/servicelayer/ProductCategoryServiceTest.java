package com.online.shopping.servicelayer;

import com.online.shopping.entity.ProductCategory;
import com.online.shopping.repository.ProductCategoryRepository;
import com.online.shopping.requestdto.ProductCategoryRequestDto;
import com.online.shopping.responsedto.ProductCategoryResponseDto;
import com.online.shopping.services.ProductCategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

//@ExtendWith(MockitoExtension.class)
public class ProductCategoryServiceTest {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void should_save_product_category(){
//        ProductCategoryRequestDto categoryRequestDto = new ProductCategoryRequestDto(1, "Electronics");
//        ProductCategoryResponseDto savedProductCategory = productCategoryService.addCategory(categoryRequestDto);
//        assertThat(savedProductCategory).isNotNull();
    }

}
