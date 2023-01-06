package com.online.shopping.controller;

import com.online.shopping.requestdto.ProductSubcategoryRequestDto;
import com.online.shopping.responsedto.ProductSubcategoryResponseDto;
import com.online.shopping.services.ProductSubcategoryService;
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
@RequestMapping("/subcategories")
public class ProductSubcategoryController {

    @Autowired
    private ProductSubcategoryService subcategoryService;

    @PreAuthorize("hasRole('ROLE_Customer') or hasRole('ROLE_Manager')")
    @GetMapping
    public List<ProductSubcategoryResponseDto> getAllSubcategory() {
        return subcategoryService.getAllSubcategory();
    }

    @PreAuthorize("hasRole('ROLE_Customer')")
    @GetMapping("/{subcategoryId}")
    public ProductSubcategoryResponseDto getSingleSubcategory(@PathVariable int subcategoryId) {
        return subcategoryService.getSingleSubcategory(subcategoryId);
    }

    @PreAuthorize("hasRole('ROLE_Customer')")
    @GetMapping("/categories/{categoryId}")
    public List<ProductSubcategoryResponseDto> getSubcategoriesOfCategory(@PathVariable int categoryId) {
        return subcategoryService.getSubcategoriesOfCategory(categoryId);
    }

    @PreAuthorize("hasRole('ROLE_Manager')")
    @PostMapping
    public ProductSubcategoryResponseDto addSubcategory(@Valid @RequestBody ProductSubcategoryRequestDto subcategoryDto) {
        return subcategoryService.addSubcategory(subcategoryDto);
    }

    @PreAuthorize("hasRole('ROLE_Manager')")
    @PutMapping("/inactivateStatus/{subcategoryId}")
    public ProductSubcategoryResponseDto inactivateSubcategoryStatus(@PathVariable int subcategoryId) {
        return subcategoryService.inactivateSubcategoryStatus(subcategoryId);
    }

    @PreAuthorize("hasRole('ROLE_Manager')")
    @PutMapping("/activateStatus/{subcategoryId}")
    public ProductSubcategoryResponseDto activateSubcategoryStatus(@PathVariable int subcategoryId) {
        return subcategoryService.activateSubcategoryStatus(subcategoryId);
    }

    @PreAuthorize("hasRole('ROLE_Manager')")
    @DeleteMapping("/{subcategoryId}")
    public ProductSubcategoryResponseDto removeSubcategory(@PathVariable int subcategoryId) {
        return subcategoryService.removeSubcategory(subcategoryId);
    }

}
