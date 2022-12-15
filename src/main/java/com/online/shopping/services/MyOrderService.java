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
import java.util.Objects;
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

    public void saveMyOrder(FinalOrder finalOrder, String authorization, OrderStatus status) {
        User user = userRepository.findById(validate.getUserId(authorization)).orElse(null);
        Product product = finalOrder.getProduct();
        String modelNumber = highlightsRepository.findModelNumberById(product.getHighlights().getId());
        MyOrders myOrder = myOrderMapper.convertFinalOrderToMyOrder(finalOrder, product);
        myOrder.setModelNumber(modelNumber);
        myOrder.setUser(user);
        myOrder.setOrderStatus(status);
        myOrderRepository.save(myOrder);
    }

    public List<MyOrderResponseDto> getAllCustomerMyOrders() {
        return myOrderRepository.findAll().stream().map(myOrder -> myOrderMapper.convertEntityToDto(myOrder)).collect(Collectors.toList());
    }

    public List<MyOrderResponseDto> getAllMyOrders(String authorization) {
        return myOrderRepository.findAllByUserId(validate.getUserId(authorization)).stream().map(myOrder -> myOrderMapper.convertEntityToDto(myOrder)).collect(Collectors.toList());
    }

    public MyOrderResponseDto getSingleMyOrder(int myOrderId) throws GeneralException {
        MyOrders myOrders = myOrderRepository.findById(myOrderId).orElse(null);
        if (!Objects.nonNull(myOrders)) {
            throw new GeneralException(ErrorConstants.MY_ORDER_NOT_FOUND_ERROR + myOrderId);
        }
        return myOrderMapper.convertEntityToDto(myOrders);
    }

    public List<PriceDetailResponseDto> getProductPresentDetails(int myOrderId) {
        String productName = myOrderRepository.findProductNameById(myOrderId);
        int productId = productRepository.findIdByProductName(productName);
        return priceDetailRepository.findAllByProductId(productId).stream().map(priceDetail -> priceDetailMapper.convertEntityToDto(priceDetail)).collect(Collectors.toList());
    }

    public String removeSingleMyOrder(int myOrderId) {
        MyOrders myOrder = myOrderRepository.findById(myOrderId).orElse(null);
        if (!Objects.nonNull(myOrder)) {
            throw new GeneralException(ErrorConstants.MY_ORDER_NOT_FOUND_ERROR + myOrderId);
        }
        myOrderRepository.delete(myOrder);
        return "Successfully deleted my-order";
    }

}
