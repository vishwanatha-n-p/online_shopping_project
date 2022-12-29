package com.online.shopping.controller;

import com.online.shopping.requestdto.ProductTypeRequestDto;
import com.online.shopping.responsedto.ProductTypeResponseDto;
import com.online.shopping.services.ProductTypeService;
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
@RequestMapping("/productTypes")
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    @PreAuthorize("hasRole('ROLE_Customer') or hasRole('ROLE_Manager')")
    @GetMapping
    public List<ProductTypeResponseDto> getAllProductTypes() {
        return productTypeService.getAllProductTypes();
    }

    @PreAuthorize("hasRole('ROLE_Customer')")
    @GetMapping("/{productTypeId}")
    public ProductTypeResponseDto getSingleProductType(@PathVariable int productTypeId) {
        return productTypeService.getSingleProductType(productTypeId);
    }

    @PreAuthorize("hasRole('ROLE_Customer')")
    @GetMapping("/subcategories/{subcategoryId}")
    public List<ProductTypeResponseDto> getParticularSubcategoryProductTypes(@PathVariable int subcategoryId) {
        return productTypeService.getParticularSubcategoryProductTypes(subcategoryId);
    }

    @PreAuthorize("hasRole('ROLE_Manager')")
    @PostMapping
    public ProductTypeResponseDto addProductType(@Valid @RequestBody ProductTypeRequestDto productTypeRequestDto) {
        return productTypeService.addProductType(productTypeRequestDto);
    }

    @PreAuthorize("hasRole('ROLE_Seller') or hasRole('ROLE_Manager')")
    @PutMapping("/inactivateStatus/{productTypeId}")
    public ProductTypeResponseDto inactivateProductTypeStatus(@PathVariable int productTypeId) {
        return productTypeService.activateProductTypeStatus(productTypeId);
    }

    @PreAuthorize("hasRole('ROLE_Seller') or hasRole('ROLE_Manager')")
    @PutMapping("/activateStatus/{productTypeId}")
    public ProductTypeResponseDto activateProductTypeStatus(@PathVariable int productTypeId) {
        return productTypeService.activateProductTypeStatus(productTypeId);
    }

    @PreAuthorize("hasRole('ROLE_Seller') or hasRole('ROLE_Manager')")
    @DeleteMapping("/{productTypeId}")
    public ProductTypeResponseDto removeProductType(@PathVariable int productTypeId) {
        return productTypeService.removeProductType(productTypeId);
    }

}
