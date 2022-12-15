package com.online.shopping.controller;

import com.online.shopping.requestdto.PriceDetailRequestDto;
import com.online.shopping.responsedto.PriceDetailResponseDto;
import com.online.shopping.services.PriceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/priceDetails")
public class PriceDetailController {

    @Autowired
    private PriceDetailService priceDetailService;

    @PreAuthorize("hasRole('ROLE_Customer') or hasRole('ROLE_Manager')")
    @GetMapping
    public List<PriceDetailResponseDto> getAllPriceDetail() {
        return priceDetailService.getAllPriceDetail();
    }

    @PreAuthorize("hasRole('ROLE_Customer')")
    @GetMapping("/{priceDetailId}")
    public PriceDetailResponseDto getSinglePriceDetail(@PathVariable int priceDetailId) {
        return priceDetailService.getSinglePriceDetail(priceDetailId);
    }

    @PreAuthorize("hasRole('ROLE_Customer')")
    @GetMapping("/products/{productId}")
    public List<PriceDetailResponseDto> getProductPriceDetail(@PathVariable int productId) {
        return priceDetailService.getProductPriceDetail(productId);
    }

    @PreAuthorize("hasRole('ROLE_Seller')")
    @PostMapping
    public PriceDetailResponseDto addPriceDetail(@Valid @RequestBody PriceDetailRequestDto priceDetailRequestDto) {
        return priceDetailService.addPriceDetail(priceDetailRequestDto);
    }

    @PreAuthorize("hasRole('ROLE_Seller')")
    @DeleteMapping("/{priceDetailId}")
    public String removePriceDetail(@PathVariable int priceDetailId) {
        return priceDetailService.removePriceDetail(priceDetailId);
    }

}
