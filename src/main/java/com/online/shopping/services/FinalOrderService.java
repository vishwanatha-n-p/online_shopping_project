package com.online.shopping.services;

import com.online.shopping.constants.ErrorConstants;
import com.online.shopping.entity.FinalOrder;
import com.online.shopping.entity.Payment;
import com.online.shopping.entity.ProductOrder;
import com.online.shopping.enums.OrderStatus;
import com.online.shopping.exception.GeneralException;
import com.online.shopping.general.RandomNumber;
import com.online.shopping.general.Validate;
import com.online.shopping.mapper.FinalOrderMapper;
import com.online.shopping.repository.FinalOrderRepository;
import com.online.shopping.responsedto.FinalOrderResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FinalOrderService {

    @Autowired
    private FinalOrderRepository finalOrderRepository;

    @Autowired
    private FinalOrderMapper finalOrderMapper;

    @Autowired
    private RandomNumber randomNumber;

    @Autowired
    private Validate validate;

    @Autowired
    private MyOrderService myOrderService;

    public List<FinalOrderResponseDto> getAllFinalOrder() {
        return finalOrderRepository.findAll().stream().map(f -> finalOrderMapper.convertEntityToDto(f)).collect(Collectors.toList());
    }

    public void saveFinalOrder(List<ProductOrder> productOrders, Payment paymentResponse) {
        productOrders.stream().forEach(productOrder -> {
            FinalOrder finalOrder = finalOrderMapper.convertProductOrderToFinalOrder(productOrder);
            finalOrder.setOrderStatus(OrderStatus.ORDER_PLACED);
            finalOrder.setOrderDate(LocalDateTime.now());
            finalOrder.setShipDate(LocalDate.now().plusDays(randomNumber.getRandomNumber()));
            finalOrder.setDeliveryDate(LocalDate.now().plusDays(randomNumber.getRandomNumber()));
            finalOrder.setPayment(paymentResponse);
            finalOrderRepository.save(finalOrder);
        });
    }

    public List<FinalOrderResponseDto> getUserFinalOrder(String authorization) {
        List<FinalOrder> finalOrders = finalOrderRepository.findAllByUserId(validate.getUserId(authorization));
        if (Objects.nonNull(finalOrders)) {
            return finalOrders.stream().map(f -> finalOrderMapper.convertEntityToDto(f)).collect(Collectors.toList());
        } else {
            throw new GeneralException(ErrorConstants.FINAL_ORDER_NOT_FOUND_ERROR);
        }
    }

    public String cancelFinalOrder(int finalOrderId, String authorization) {
        FinalOrder finalOrder = finalOrderRepository.findById(finalOrderId).orElse(null);
        if (Objects.nonNull(finalOrder)) {
//            finalOrder.setOrderStatus(OrderStatus.CANCELED);
            myOrderService.saveMyOrder(finalOrder, authorization, OrderStatus.CANCELED);
            finalOrderRepository.delete(finalOrder);
//            return finalOrderMapper.convertEntityToDto(finalOrderRepository.save(finalOrder));
            return "Your Order is canceled";
        }
        throw new GeneralException(ErrorConstants.ORDER_NOT_FOUND_ERROR);
    }

    public String deliverFinalOrder(int finalOrderId, String authorization) {
        FinalOrder finalOrder = finalOrderRepository.findById(finalOrderId).orElse(null);
        if (Objects.nonNull(finalOrder)) {
//            finalOrder.setOrderStatus(OrderStatus.DELIVERED);
            myOrderService.saveMyOrder(finalOrder, authorization, OrderStatus.DELIVERED);
            finalOrderRepository.delete(finalOrder);
//            return finalOrderMapper.convertEntityToDto(finalOrderRepository.save(finalOrder));
            return "Your Order is delivered";
        }
        throw new GeneralException(ErrorConstants.ORDER_NOT_FOUND_ERROR);
    }
}
