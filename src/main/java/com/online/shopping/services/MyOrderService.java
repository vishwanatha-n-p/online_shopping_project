package com.online.shopping.services;

import com.online.shopping.constants.ErrorConstants;
import com.online.shopping.entity.FinalOrder;
import com.online.shopping.entity.MyOrders;
import com.online.shopping.entity.Product;
import com.online.shopping.entity.User;
import com.online.shopping.enums.OrderStatus;
import com.online.shopping.exception.GeneralException;
import com.online.shopping.general.Validate;
import com.online.shopping.mapper.MyOrderMapper;
import com.online.shopping.mapper.PriceDetailMapper;
import com.online.shopping.repository.HighlightsRepository;
import com.online.shopping.repository.MyOrderRepository;
import com.online.shopping.repository.PriceDetailRepository;
import com.online.shopping.repository.ProductRepository;
import com.online.shopping.repository.UserRepository;
import com.online.shopping.responsedto.MyOrderResponseDto;
import com.online.shopping.responsedto.PriceDetailResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyOrderService {

    @Autowired
    private MyOrderRepository myOrderRepository;

    @Autowired
    private Validate validate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MyOrderMapper myOrderMapper;

    @Autowired
    private HighlightsRepository highlightsRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PriceDetailRepository priceDetailRepository;

    @Autowired
    private PriceDetailMapper priceDetailMapper;

    public MyOrderResponseDto saveCancelledOrder(FinalOrder finalOrder, String authorization, OrderStatus status) {
        User user = userRepository.findById(validate.getUserId(authorization)).orElse(null);
        Product product = finalOrder.getProduct();
        String modelNumber = highlightsRepository.findModelNumberById(product.getHighlights().getId());
        MyOrders myOrder = myOrderMapper.convertFinalOrderToMyOrder(finalOrder, product);
        myOrder.setModelNumber(modelNumber);
        myOrder.setUser(user);
        myOrder.setOrderStatus(status);
        return myOrderMapper.convertEntityToDto(myOrderRepository.save(myOrder));
    }

    public MyOrderResponseDto saveDeliverOrder(FinalOrder finalOrder, OrderStatus status) {
        int userId = finalOrder.getPayment().getCustomerDetail().getUser().getId();
        User user = userRepository.findById(userId).orElseThrow(() -> new GeneralException(ErrorConstants.USER_NOT_FOUND_ERROR));
        Product product = finalOrder.getProduct();
        String modelNumber = highlightsRepository.findModelNumberById(product.getHighlights().getId());
        MyOrders myOrder = myOrderMapper.convertFinalOrderToMyOrder(finalOrder, product);
        myOrder.setModelNumber(modelNumber);
        myOrder.setUser(user);
        myOrder.setOrderStatus(status);
        return myOrderMapper.convertEntityToDto(myOrderRepository.save(myOrder));
    }

    public List<MyOrderResponseDto> getAllCustomerMyOrders() {
        return myOrderRepository.findAll().stream().map(myOrder -> myOrderMapper.convertEntityToDto(myOrder)).collect(Collectors.toList());
    }

    public List<MyOrderResponseDto> getAllMyOrders(String authorization) {
        return myOrderRepository.findAllByUserId(validate.getUserId(authorization)).stream().map(myOrder -> myOrderMapper.convertEntityToDto(myOrder)).collect(Collectors.toList());
    }

    public MyOrderResponseDto getSingleMyOrder(int myOrderId) throws GeneralException {
        MyOrders myOrder = myOrderRepository.findById(myOrderId).orElseThrow(() -> new GeneralException(ErrorConstants.USER_NOT_FOUND_ERROR));
        return myOrderMapper.convertEntityToDto(myOrder);
    }

    public List<PriceDetailResponseDto> getProductPresentDetails(int myOrderId) {
        MyOrders myOrder = myOrderRepository.findProductNameById(myOrderId);
        Product product = productRepository.findIdByProductName(myOrder.getProductName());
        return priceDetailRepository.findAllByProductId(product.getId()).stream().map(priceDetail -> priceDetailMapper.convertEntityToDto(priceDetail)).collect(Collectors.toList());
    }

    public MyOrderResponseDto removeSingleMyOrder(int myOrderId) {
        MyOrders myOrder = myOrderRepository.findById(myOrderId).orElseThrow(() -> new GeneralException(ErrorConstants.ORDER_NOT_FOUND_ERROR));
        myOrderRepository.delete(myOrder);
        return myOrderMapper.convertEntityToDto(myOrder);
    }

}
