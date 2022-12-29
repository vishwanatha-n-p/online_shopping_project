package com.online.shopping.servicelayer;

import com.online.shopping.entity.Highlights;
import com.online.shopping.requestdto.ProductRequestDto;
import com.online.shopping.responsedto.PriceDetailResponseDto;
import com.online.shopping.responsedto.ProductResponseDto;
import com.online.shopping.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    public void test_addProduct() {
        Highlights highlightsRequest = new Highlights("X515JA-EJ362WS | X515JA-EJ392WS", "Intel Core i3/ 8 GB / 512 GB SSD / Windows 11 Home / With MS Office/ Battery Backup Upto 9.5 hours", "360.2 x 234.9 x 19.9 mm | Weight 1.80 kg | 39.62 cm (15.6 inch)");
        ProductRequestDto productRequest = new ProductRequestDto("ASUS VivoBook 15 (2022) Core i3 10th Gen ", 9, "Eclipse Gray", highlightsRequest, 7);
        String securityToken = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJGb25lem9uIiwiZXhwIjoxNjcyMzI0NzMwLCJpYXQiOjE2NzIzMDY3MzB9.VNAT566a_k5DRMKONnwwll0BryaLQciJRSRrG5hHox4opFd1lt6F_a0LZ9Xh3UesP0q8WSPvA16_o657GSah0A";
        ProductResponseDto productResponse = productService.addProduct(securityToken, productRequest);
        assertEquals(productRequest.getProductName(), productResponse.getProductName());
        assertEquals(productRequest.getColor(), productResponse.getColor());
        assertEquals(productRequest.getProductTypeId(), productResponse.getProductType().getId());
    }

    @Test
    public void test_getAllProducts() {
        List<ProductResponseDto> products = productService.getAllProducts();
        assertThat(products.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void test_getSingleProduct() {
        int productId = 3;
        ProductResponseDto productResponse = productService.getSingleProduct(productId);
        assertEquals(productId, productResponse.getId());
    }

    @Test
    public void test_getParticularProductTypeProducts() {
        int productTypeId = 4;
        List<ProductResponseDto> products = productService.getParticularProductTypeProducts(productTypeId);
        assertThat(products.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void test_getProductPriceDetail() {
        int productId = 1;
        List<PriceDetailResponseDto> priceDetails = productService.getProductPriceDetail(productId);
        assertThat(priceDetails.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void test_deactivateProduct() {
        int productId = 10;
        ProductResponseDto productResponse = productService.deactivateProduct(productId);
        assertEquals(productId, productResponse.getId());
    }

    @Test
    public void test_activateProduct() {
        int productId = 10;
        ProductResponseDto productResponse = productService.activateProduct(productId);
        assertEquals(productId, productResponse.getId());
    }

    @Test
    public void test_deleteProduct() {
        int productId = 11;
        ProductResponseDto productResponse = productService.deleteProduct(productId);
        assertEquals(productId, productResponse.getId());
    }

    @Test
    public void test_deleteSellersProduct() {
        int sellerId = 2, productId = 11;
        ProductResponseDto productResponse = productService.deleteSellersProduct(sellerId, productId);
        assertEquals(productId, productResponse.getId());
    }

}
