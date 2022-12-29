package com.online.shopping.controller;

import com.online.shopping.requestdto.AddressRequestDto;
import com.online.shopping.responsedto.AddressResponseDto;
import com.online.shopping.services.AddressService;
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
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PreAuthorize("hasRole('ROLE_Customer')")
    @PostMapping
    public AddressResponseDto addAddress(@Valid @RequestBody AddressRequestDto addressRequestDto, @RequestHeader String authorization) {
        return addressService.addAddress(addressRequestDto, authorization);
    }

    @PreAuthorize("hasRole('ROLE_Manager')")
    @GetMapping
    public List<AddressResponseDto> getAllAddress() {
        return addressService.getAllAddress();
    }

    @PreAuthorize("hasRole('ROLE_Customer')")
    @GetMapping("/user")
    public List<AddressResponseDto> getCustomerAddress(@RequestHeader String authorization) {
        return addressService.getCustomerAddress(authorization);
    }

    @PreAuthorize("hasRole('ROLE_Customer')")
    @GetMapping("/{addressId}")
    public AddressResponseDto getSingleAddress(@PathVariable int addressId) {
        return addressService.getSingleAddress(addressId);
    }

    @PreAuthorize("hasRole('ROLE_Manager')")
    @DeleteMapping("/{addressId}")
    public AddressResponseDto removeAddress(@PathVariable int addressId) {
        return addressService.removeAddress(addressId);
    }

}
