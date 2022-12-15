package com.online.shopping.controller;

import com.online.shopping.requestdto.ProductRequestDto;
import com.online.shopping.responsedto.PriceDetailResponseDto;
import com.online.shopping.responsedto.ProductResponseDto;
import com.online.shopping.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @PreAuthorize("hasRole('ROLE_Seller')")
    @PostMapping("/sellers/{sellerId}")
    public ProductResponseDto addProduct(@Valid @PathVariable int sellerId, @RequestBody ProductRequestDto productRequestDto) {
        return productService.addProduct(sellerId, productRequestDto);
    }

    @PreAuthorize("hasRole('ROLE_Manager')")
    @GetMapping
    public List<ProductResponseDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @PreAuthorize("hasRole('ROLE_Customer')")
    @GetMapping("/{productId}")
    public ProductResponseDto getSingleProduct(@PathVariable int productId) {
        return productService.getSingleProduct(productId);
    }

    @PreAuthorize("hasRole('ROLE_Customer')")
    @GetMapping("/productTypes/{productTypeId}")
    public List<ProductResponseDto> getParticularProductTypeProducts(@PathVariable int productTypeId) {
        return productService.getParticularProductTypeProducts(productTypeId);
    }

    @PreAuthorize("hasRole('ROLE_Customer')")
    @GetMapping("/priceDetails/{productId}")
    public List<PriceDetailResponseDto> getProductPriceDetail(@PathVariable int productId) {
        return productService.getProductPriceDetail(productId);
    }

    @PreAuthorize("hasRole('ROLE_Manager')")
    @PutMapping("/deactivate/{productId}")
    public String deactivateProduct(@PathVariable int productId) {
        return productService.deactivateProduct(productId);
    }

    @PreAuthorize("hasRole('ROLE_Manager')")
    @PutMapping("/activate/{productId}")
    public String activateProduct(@PathVariable int productId) {
        return productService.activateProduct(productId);
    }

    @PreAuthorize("hasRole('ROLE_Seller')")
    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable int productId) {
        return productService.deleteProduct(productId);
    }

    @PreAuthorize("hasRole('ROLE_Seller')")
    @DeleteMapping("/{productId}/sellers/{sellerId}")
    public String deleteProduct(@PathVariable int sellerId, @PathVariable int productId) {
        return productService.deleteSellersProduct(sellerId, productId);
    }

}
