package com.online.shopping.services;

import com.online.shopping.constants.ErrorConstants;
import com.online.shopping.entity.Currency;
import com.online.shopping.exception.CurrencyNotFoundException;
import com.online.shopping.mapper.CurrencyMapper;
import com.online.shopping.repository.CurrencyRepository;
import com.online.shopping.requestdto.CurrencyRequestDto;
import com.online.shopping.responsedto.CurrencyResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    public CurrencyResponseDto addCurrency(CurrencyRequestDto currencyRequestDto) {
        Optional<Currency> currency = currencyRepository.findById(currencyRequestDto.getId());
        if (!currency.isPresent()) {
            return currencyMapper.convertEntityToDto(currencyRepository.save(currencyMapper.convertDtoToEntity(currencyRequestDto)));
        }
        throw new CurrencyNotFoundException(ErrorConstants.CURRENCY_EXIST_ERROR);
    }

    public List<CurrencyResponseDto> getAllCurrency() {
        return currencyRepository.findAll().stream().map(currency -> currencyMapper.convertEntityToDto(currency)).collect(Collectors.toList());
    }

    public CurrencyResponseDto getSingleCurrency(int currencyId) {
        Currency currency = currencyRepository.findById(currencyId).orElseThrow(() -> new CurrencyNotFoundException(ErrorConstants.CURRENCY_NOT_FOUND_ERROR + currencyId));
        return currencyMapper.convertEntityToDto(currency);
    }

    public CurrencyResponseDto removeCurrency(int currencyId) {
        Currency currency = currencyRepository.findById(currencyId).orElseThrow(() -> new CurrencyNotFoundException(ErrorConstants.CURRENCY_NOT_FOUND_ERROR + currencyId));
        try {
            currencyRepository.delete(currency);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(ErrorConstants.CURRENCY_ALREADY_USED_ERROR);
        }
        return currencyMapper.convertEntityToDto(currency);
    }

}
