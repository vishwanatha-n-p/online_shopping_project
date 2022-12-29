package com.online.shopping.controller;

import com.online.shopping.requestdto.SellerRequestDto;
import com.online.shopping.responsedto.SellerResponseDto;
import com.online.shopping.services.SellerService;
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
@RequestMapping("/sellers")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @PreAuthorize("hasRole('ROLE_Manager')")
    @GetMapping
    public List<SellerResponseDto> getAllSeller() {
        return sellerService.getAllSeller();
    }

    @PreAuthorize("hasRole('ROLE_Manager')")
    @GetMapping("/{sellerId}")
    public SellerResponseDto getSingleSeller(@PathVariable int sellerId) {
        return sellerService.getSingleSeller(sellerId);
    }

    @PreAuthorize("hasRole('ROLE_Seller')")
    @PostMapping
    public SellerResponseDto addSeller(@Valid @RequestBody SellerRequestDto sellerRequestDto, @RequestHeader String authorization) {
        return sellerService.addSeller(sellerRequestDto, authorization);
    }

    @PreAuthorize("hasRole('ROLE_Manager')")
    @DeleteMapping("/{sellerId}")
    public SellerResponseDto removeSeller(@PathVariable int sellerId) {
        return sellerService.removeSeller(sellerId);
    }

}
