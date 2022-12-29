package com.online.shopping.servicelayer;

import com.online.shopping.requestdto.ProductTypeRequestDto;
import com.online.shopping.responsedto.ProductTypeResponseDto;
import com.online.shopping.services.ProductTypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductTypeServiceTest {

    @Autowired
    private ProductTypeService productTypeService;

    @Test
    public void test_addProductType() {
        ProductTypeRequestDto productTypeRequest = new ProductTypeRequestDto("Dining Tables & Chairs", 5);
        ProductTypeResponseDto productTypeResponse = productTypeService.addProductType(productTypeRequest);
        assertEquals(productTypeRequest.getProductType(), productTypeResponse.getProductType());
        assertEquals(productTypeRequest.getSubcategoryId(), productTypeResponse.getProductSubcategory().getId());
    }

    @Test
    public void test_getAllProductTypes() {
        List<ProductTypeResponseDto> productTypes = productTypeService.getAllProductTypes();
        assertThat(productTypes.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void test_getSingleProductType() {
        int productTypeId = 2;
        ProductTypeResponseDto productTypeResponse = productTypeService.getSingleProductType(productTypeId);
        assertEquals(productTypeId, productTypeResponse.getId());
    }

    @Test
    public void test_getParticularSubcategoryProductTypes() {
        int subcategoryId = 4;
        List<ProductTypeResponseDto> productTypes = productTypeService.getParticularSubcategoryProductTypes(subcategoryId);
        assertThat(productTypes.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void test_inactivateProductTypeStatus() {
        int productTypeId = 13;
        ProductTypeResponseDto productTypeResponse = productTypeService.inactivateProductTypeStatus(productTypeId);
        assertEquals(productTypeId, productTypeResponse.getId());
    }

    @Test
    public void test_activateProductTypeStatus() {
        int productTypeId = 4;
        ProductTypeResponseDto productTypeResponse = productTypeService.activateProductTypeStatus(productTypeId);
        assertEquals(productTypeId, productTypeResponse.getId());
    }

    @Test
    public void test_removeProductType() {
        int productTypeId = 6;
        ProductTypeResponseDto productType = productTypeService.removeProductType(productTypeId);
        assertEquals(productTypeId, productType.getId());
    }

}
