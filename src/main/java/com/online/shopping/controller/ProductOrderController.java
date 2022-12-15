package com.online.shopping.controller;

import com.online.shopping.requestdto.OrderFromProductRequestDto;
import com.online.shopping.requestdto.OrderFromPriceDetailRequestDto;
import com.online.shopping.responsedto.ProductOrderResponseDto;
import com.online.shopping.services.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/productOrders")
public class ProductOrderController {

    @Autowired
    private ProductOrderService productOrderService;

    @PreAuthorize("hasRole('ROLE_Manager')")
    @GetMapping
    public List<ProductOrderResponseDto> getAllProductOrders() {
        return productOrderService.getAllProductOrders();
    }

    @PreAuthorize("hasRole('ROLE_Customer')")
    @GetMapping("/userOrder")
    public List<ProductOrderResponseDto> getUserProductOrders(@RequestHeader String authorization) {
        return productOrderService.getUserProductOrders(authorization);
    }

    @PreAuthorize("hasRole('ROLE_Customer')")
    @PostMapping
    public ProductOrderResponseDto addProductOrder(@Valid @RequestBody OrderFromProductRequestDto productOrderRequestDto, @RequestHeader String authorization) {
        return productOrderService.addProductOrder(productOrderRequestDto, authorization);
    }

    @PreAuthorize("hasRole('ROLE_Customer')")
    @PostMapping("/priceDetails")
    public ProductOrderResponseDto addProductFromPriceDetail(@Valid @RequestBody OrderFromPriceDetailRequestDto productOrderRequestDto, @RequestHeader String authorization) {
        return productOrderService.addProductFromPriceDetail(productOrderRequestDto, authorization);
    }

    @PreAuthorize("hasRole('ROLE_Customer')")
    @DeleteMapping("/{productOrderId}")
    public String removeProductOrder(@PathVariable int productOrderId) {
        return productOrderService.removeProductOrder(productOrderId);
    }

}
