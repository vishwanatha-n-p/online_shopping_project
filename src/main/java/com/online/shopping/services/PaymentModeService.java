package com.online.shopping.services;

import com.online.shopping.constants.ErrorConstants;
import com.online.shopping.entity.PaymentMode;
import com.online.shopping.exception.GeneralException;
import com.online.shopping.mapper.PaymentModeMapper;
import com.online.shopping.repository.PaymentModeRepository;
import com.online.shopping.requestdto.PaymentModeRequestDto;
import com.online.shopping.responsedto.PaymentModeResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentModeService {

    @Autowired
    private PaymentModeRepository paymentModeRepository;

    @Autowired
    private PaymentModeMapper paymentModeMapper;

    public PaymentModeResponseDto addPaymentMode(PaymentModeRequestDto paymentModeRequestDto) {
        Optional<PaymentMode> paymentMode = paymentModeRepository.findByModeName(paymentModeRequestDto.getModeName());
        if (!paymentMode.isPresent()) {
            return paymentModeMapper.convertEntityToDto(paymentModeRepository.save(paymentModeMapper.convertDtoToEntity(paymentModeRequestDto)));
        }
        throw new GeneralException(ErrorConstants.PAYMENT_MODE_EXIST_ERROR);
    }

    public List<PaymentModeResponseDto> getAllPaymentMode() {
        return paymentModeRepository.findAll().stream().map(paymentMode -> paymentModeMapper.convertEntityToDto(paymentMode)).collect(Collectors.toList());
    }

    public PaymentModeResponseDto getSinglePaymentMode(int paymentModeId) {
        return paymentModeMapper.convertEntityToDto(paymentModeRepository.findById(paymentModeId).orElseThrow(() -> new GeneralException(ErrorConstants.PAYMENT_MODE_NOT_FOUND_ERROR)));
    }

    public PaymentModeResponseDto removePaymentMode(int paymentModeId) {
        PaymentMode paymentMode = paymentModeRepository.findById(paymentModeId).orElseThrow(() -> new GeneralException(ErrorConstants.PAYMENT_MODE_NOT_FOUND_ERROR));
        try {
            paymentModeRepository.deleteById(paymentModeId);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(ErrorConstants.HIGHLIGHTS_ALREADY_USED_ERROR);
        }
        return paymentModeMapper.convertEntityToDto(paymentMode);
    }

}
