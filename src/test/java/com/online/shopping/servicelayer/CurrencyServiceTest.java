package com.online.shopping.servicelayer;

import com.online.shopping.requestdto.CurrencyRequestDto;
import com.online.shopping.responsedto.CurrencyResponseDto;
import com.online.shopping.services.CurrencyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CurrencyServiceTest {

    @Autowired
    private CurrencyService currencyService;

    @Test
    public void test_addCurrency() {
        CurrencyRequestDto currencyRequest = new CurrencyRequestDto("United Kingdom", "GBP");
        CurrencyResponseDto currencyResponse = currencyService.addCurrency(currencyRequest);
        assertEquals(currencyRequest.getCountry(), currencyResponse.getCountry());
    }

    @Test
    public void test_getAllCurrency() {
        List<CurrencyResponseDto> currencies = currencyService.getAllCurrency();
        assertThat(currencies.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void test_getSingleCurrency() {
        int currencyId = 1;
        CurrencyResponseDto currencyResponse = currencyService.getSingleCurrency(currencyId);
        assertEquals(currencyId, currencyResponse.getId());
    }

    @Test
    public void test_removeCurrency() {
        int currencyId = 2;
        CurrencyResponseDto currencyResponse = currencyService.removeCurrency(currencyId);
        assertEquals(currencyId, currencyResponse.getId());
    }

}
