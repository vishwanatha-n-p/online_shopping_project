package com.online.shopping.services;

import com.online.shopping.entity.PaymentMode;
import com.online.shopping.exception.GeneralException;
import com.online.shopping.mapper.PaymentModeMapper;
import com.online.shopping.repository.PaymentModeRepository;
import com.online.shopping.requestdto.PaymentModeRequestDto;
import com.online.shopping.responsedto.PaymentModeResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
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

    public PaymentModeResponseDto addPaymentMode(PaymentModeRequestDto paymentModeRequestDto){
        Optional<PaymentMode> paymentMode=paymentModeRepository.findByModeName(paymentModeRequestDto.getModeName());
        if(!paymentMode.isPresent()){
            return paymentModeMapper.convertEntityToDto(paymentModeRepository.save(paymentModeMapper.convertDtoToEntity(paymentModeRequestDto)));
        }
        return paymentModeMapper.convertEntityToDto(paymentModeRepository.save(paymentMode.get()));
    }

    public List<PaymentModeResponseDto> getAllPaymentMode(){
        return paymentModeRepository.findAll().stream().map(paymentMode -> paymentModeMapper.convertEntityToDto(paymentMode)).collect(Collectors.toList());
    }

    public PaymentMode getSinglePaymentMode(int paymentModeId){
        return paymentModeRepository.findById(paymentModeId).orElseThrow(() -> new GeneralException("Payment mode not found"));
    }

    public String removePaymentMode(int paymentModeId){
        paymentModeRepository.findById(paymentModeId).orElseThrow(() -> new GeneralException("Payment mode not found"));
        paymentModeRepository.deleteById(paymentModeId);
        return "Successfully deleted the Payment mode where Id: " + paymentModeId;
    }

}
