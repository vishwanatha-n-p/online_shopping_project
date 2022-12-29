package com.online.shopping.controller;

import com.online.shopping.requestdto.ProductCategoryRequestDto;
import com.online.shopping.responsedto.ProductCategoryResponseDto;
import com.online.shopping.services.ProductCategoryService;
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
@RequestMapping("/api/categories")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService categoryService;

    @GetMapping
    public List<ProductCategoryResponseDto> getAllCategory() {
        return categoryService.getAllCategory();
    }

    @PreAuthorize("hasRole('ROLE_Customer')")
    @GetMapping("/{categoryId}")
    public ProductCategoryResponseDto getSingleCategory(@PathVariable int categoryId) {
        return categoryService.getSingleCategory(categoryId);
    }

    @PreAuthorize("hasRole('ROLE_Manager')")
    @PostMapping
    public ProductCategoryResponseDto addCategory(@Valid @RequestBody ProductCategoryRequestDto categoryDto) {
        return categoryService.addCategory(categoryDto);
    }

    @PreAuthorize("hasRole('ROLE_Manager')")
    @PutMapping("/inactiveStatus/{categoryId}")
    public ProductCategoryResponseDto inactivateCategoryStatus(@PathVariable int categoryId) {
        return categoryService.inactivateCategoryStatus(categoryId);
    }

    @PreAuthorize("hasRole('ROLE_Manager')")
    @PutMapping("/activeStatus/{categoryId}")
    public ProductCategoryResponseDto activateCategoryStatus(@PathVariable int categoryId) {
        return categoryService.activateCategoryStatus(categoryId);
    }

    @PreAuthorize("hasRole('ROLE_Manager')")
    @DeleteMapping("/{categoryId}")
    public ProductCategoryResponseDto removeCategory(@PathVariable int categoryId) {
        return categoryService.removeCategory(categoryId);
    }

}
