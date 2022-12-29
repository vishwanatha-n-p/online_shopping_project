package com.online.shopping.controller;

import com.online.shopping.requestdto.CurrencyRequestDto;
import com.online.shopping.responsedto.CurrencyResponseDto;
import com.online.shopping.services.CurrencyService;
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
@RequestMapping("/currencies")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @PreAuthorize("hasRole('ROLE_Manager')")
    @PostMapping
    public CurrencyResponseDto addCurrency(@Valid @RequestBody CurrencyRequestDto currencyRequestDto) {
        return currencyService.addCurrency(currencyRequestDto);
    }

    @PreAuthorize("hasRole('ROLE_Manager')")
    @GetMapping
    public List<CurrencyResponseDto> getCurrency() {
        return currencyService.getAllCurrency();
    }

    @GetMapping("/{currencyId}")
    public CurrencyResponseDto getSingleCurrency(@PathVariable int currencyId) {
        return currencyService.getSingleCurrency(currencyId);
    }

    @PreAuthorize("hasRole('ROLE_Manager')")
    @DeleteMapping("/{currencyId}")
    public CurrencyResponseDto removeCurrency(@PathVariable int currencyId) {
        return currencyService.removeCurrency(currencyId);
    }

}
