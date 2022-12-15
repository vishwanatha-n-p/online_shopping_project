package com.online.shopping.controller;

import com.online.shopping.responsedto.MyOrderResponseDto;
import com.online.shopping.responsedto.PriceDetailResponseDto;
import com.online.shopping.services.MyOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/myOrders")
public class MyOrderController {

    @Autowired
    private MyOrderService myOrderService;

    @PreAuthorize("hasRole('ROLE_Manager')")
    @GetMapping("/manager")
    public List<MyOrderResponseDto> getAllCustomerMyOrders(){
        return myOrderService.getAllCustomerMyOrders();
    }

    @PreAuthorize("hasRole('ROLE_Customer')")
    @GetMapping("/customer")
    public List<MyOrderResponseDto> getAllMyOrders(@RequestHeader String authorization){
        return myOrderService.getAllMyOrders(authorization);
    }

    @PreAuthorize("hasRole('ROLE_Customer')")
    @GetMapping("/{myOrderId}")
    public MyOrderResponseDto getSingleMyOrder(@PathVariable int myOrderId){
        return myOrderService.getSingleMyOrder(myOrderId);
    }

    @PreAuthorize("hasRole('ROLE_Customer')")
    @GetMapping("/{myOrderId}/presentDetails")
    public List<PriceDetailResponseDto> getProductPresentDetails(@PathVariable int myOrderId){
        return myOrderService.getProductPresentDetails(myOrderId);
    }

    @PreAuthorize("hasRole('ROLE_Manager')")
    @DeleteMapping("/{myOrderId}")
    public String removeSingleMyOrder(@PathVariable int myOrderId){
        return myOrderService.removeSingleMyOrder(myOrderId);
    }

}
