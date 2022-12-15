package com.online.shopping.services;

import com.online.shopping.constants.ErrorConstants;
import com.online.shopping.entity.Currency;
import com.online.shopping.exception.CurrencyNotFoundException;
import com.online.shopping.mapper.CurrencyMapper;
import com.online.shopping.repository.CurrencyRepository;
import com.online.shopping.requestdto.CurrencyRequestDto;
import com.online.shopping.responsedto.CurrencyResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private CurrencyMapper currencyMapper;

    public CurrencyResponseDto addCurrency(CurrencyRequestDto currencyRequestDto){
        Optional<Currency> currency = currencyRepository.findById(currencyRequestDto.getId());
        if (!currency.isPresent()) {
            return currencyMapper.convertEntityToDto(currencyRepository.save(currencyMapper.convertDtoToEntity(currencyRequestDto)));
        } else {
            return currencyMapper.convertEntityToDto(currencyRepository.save(currency.get()));
        }
    }

    public List<CurrencyResponseDto> getCurrency(){
        return currencyRepository.findAll().stream().map(currency -> currencyMapper.convertEntityToDto(currency)).collect(Collectors.toList());
    }

    public Currency getSingleCurrency(int currencyId){
        return currencyRepository.findById(currencyId).orElseThrow(()-> new CurrencyNotFoundException(ErrorConstants.CURRENCY_NOT_FOUND_ERROR + currencyId));
    }

    public String removeCurrency(int currencyId){
        currencyRepository.delete(currencyRepository.findById(currencyId).orElseThrow(() -> new CurrencyNotFoundException(ErrorConstants.CURRENCY_NOT_FOUND_ERROR + currencyId)));
        return "Successfully deleted the Currency where id:" + currencyId;
    }

}
