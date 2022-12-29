package com.online.shopping.controller;

import com.online.shopping.requestdto.CustomerDetailRequestDto;
import com.online.shopping.responsedto.CustomerDetailResponseDto;
import com.online.shopping.services.CustomerDetailService;
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
@RequestMapping("/customerDetails")
public class CustomerDetailController {

    @Autowired
    private CustomerDetailService customerDetailService;

    @PreAuthorize("hasRole('ROLE_Customer')")
    @PostMapping
    public CustomerDetailResponseDto addCustomerDetail(@Valid @RequestBody CustomerDetailRequestDto customerDetailRequestDto, @RequestHeader String authorization) {
        return customerDetailService.addCustomerDetail(customerDetailRequestDto, authorization);
    }

    @PreAuthorize("hasRole('ROLE_Manager')")
    @GetMapping
    public List<CustomerDetailResponseDto> getAllCustomerDetail() {
        return customerDetailService.getAllCustomerDetail();
    }

    @PreAuthorize("hasRole('ROLE_Customer')")
    @GetMapping("/user")
    public CustomerDetailResponseDto getSingleCustomerDetail(@RequestHeader String authorization) {
        return customerDetailService.getSingleCustomerDetail(authorization);
    }

    @PreAuthorize("hasRole('ROLE_Manage')")
    @DeleteMapping("/customerDetailId")
    public CustomerDetailResponseDto removeCustomerDetail(@PathVariable int customerDetailId) {
        return customerDetailService.removeCustomerDetail(customerDetailId);
    }

}
