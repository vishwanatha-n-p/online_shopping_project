package com.online.shopping.mapper;

import com.online.shopping.entity.Currency;
import com.online.shopping.requestdto.CurrencyRequestDto;
import com.online.shopping.responsedto.CurrencyResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CurrencyMapper {

    @Autowired
    private ModelMapper mapper;

    public Currency convertDtoToEntity(CurrencyRequestDto currencyRequestDto) {
        return new Currency(currencyRequestDto.getCountry(), java.util.Currency.getInstance(currencyRequestDto.getCurrencyCode()).getSymbol());
    }

    public CurrencyResponseDto convertEntityToDto(Currency currency) {
        return mapper.map(currency, CurrencyResponseDto.class);
    }

}
