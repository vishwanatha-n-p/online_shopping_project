package com.online.shopping.controller;

import com.online.shopping.requestdto.PaymentRequestDto;
import com.online.shopping.responsedto.PaymentResponseDto;
import com.online.shopping.services.EmailService;
import com.online.shopping.services.PaymentService;
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
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private EmailService emailService;

    @PreAuthorize("hasRole('ROLE_Customer')")
    @PostMapping
    public PaymentResponseDto addPayment(@Valid @RequestBody PaymentRequestDto paymentRequestDto, @RequestHeader String authorization) {
        return paymentService.addPayment(paymentRequestDto, authorization);
    }

    @PreAuthorize("hasRole('ROLE_Manager')")
    @GetMapping
    public List<PaymentResponseDto> getAllPayment() {
        return paymentService.getAllPayments();
    }

    @PreAuthorize("hasRole('ROLE_Customer')")
    @GetMapping("/{paymentId}")
    public PaymentResponseDto getSinglePayment(@PathVariable @NotNull int paymentId) {
        return paymentService.getSinglePayment(paymentId);
    }

    @PreAuthorize("hasRole('ROLE_Manager')")
    @DeleteMapping("/{paymentId}")
    public PaymentResponseDto removePayment(@PathVariable int paymentId) {
        return paymentService.removePayment(paymentId);
    }


}
