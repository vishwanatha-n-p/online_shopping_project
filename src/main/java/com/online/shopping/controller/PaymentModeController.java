package com.online.shopping.controller;

import com.online.shopping.entity.PaymentMode;
import com.online.shopping.requestdto.PaymentModeRequestDto;
import com.online.shopping.responsedto.PaymentModeResponseDto;
import com.online.shopping.services.PaymentModeService;
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
@RequestMapping("/paymentModes")
public class PaymentModeController {

    @Autowired
    private PaymentModeService paymentModeService;

    @PreAuthorize("hasRole('ROLE_Manager')")
    @PostMapping
    public PaymentModeResponseDto addPaymentMode(@Valid @RequestBody PaymentModeRequestDto paymentModeRequestDto) {
        return paymentModeService.addPaymentMode(paymentModeRequestDto);
    }

    @GetMapping
    public List<PaymentModeResponseDto> getAllPaymentMode() {
        return paymentModeService.getAllPaymentMode();
    }

    @GetMapping("/{paymentModeId}")
    public PaymentMode getSinglePaymentMode(@PathVariable int paymentModeId) {
        return paymentModeService.getSinglePaymentMode(paymentModeId);
    }

    @PreAuthorize("hasRole('ROLE_Manager')")
    @DeleteMapping("/{paymentModeId}")
    public String removePaymentMode(@PathVariable int paymentModeId) {
        return paymentModeService.removePaymentMode(paymentModeId);
    }

}
