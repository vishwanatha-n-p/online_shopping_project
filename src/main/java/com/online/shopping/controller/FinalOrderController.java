package com.online.shopping.controller;

import com.online.shopping.responsedto.FinalOrderResponseDto;
import com.online.shopping.services.FinalOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/finalOrders")
public class FinalOrderController {

    @Autowired
    private FinalOrderService finalOrderService;

    @PreAuthorize("hasRole('ROLE_Manager')")
    @GetMapping
    public List<FinalOrderResponseDto> getAllFinalOrder() {
        return finalOrderService.getAllFinalOrder();
    }

    @PreAuthorize("hasRole('ROLE_Customer')")
    @GetMapping("/user")
    public List<FinalOrderResponseDto> getUserFinalOrder(@RequestHeader String authorization) {
        return finalOrderService.getUserFinalOrder(authorization);
    }

    @PreAuthorize("hasRole('ROLE_Customer')")
    @PutMapping("/{finalOrderId}/cancel")
    public String cancelFinalOrder(@PathVariable int finalOrderId, @RequestHeader String authorization) {
        return finalOrderService.cancelFinalOrder(finalOrderId, authorization);
    }

    @PreAuthorize("hasRole('ROLE_Seller')")
    @PutMapping("/{finalOrderId}/delivered")
    public String deliverFinalOrder(@PathVariable int finalOrderId, @RequestHeader String authorization) {
        return finalOrderService.deliverFinalOrder(finalOrderId, authorization);
    }

}
