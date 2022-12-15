package com.online.shopping.services;

import com.online.shopping.constants.ErrorConstants;
import com.online.shopping.entity.PriceDetail;
import com.online.shopping.entity.Product;
import com.online.shopping.entity.ProductOrder;
import com.online.shopping.enums.ProductStatus;
import com.online.shopping.exception.GeneralException;
import com.online.shopping.general.Validate;
import com.online.shopping.mapper.ProductOrderMapper;
import com.online.shopping.repository.PriceDetailRepository;
import com.online.shopping.repository.ProductOrderRepository;
import com.online.shopping.repository.ProductRepository;
import com.online.shopping.repository.UserRepository;
import com.online.shopping.requestdto.OrderFromProductRequestDto;
import com.online.shopping.requestdto.OrderFromPriceDetailRequestDto;
import com.online.shopping.responsedto.ProductOrderResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductOrderService {

    @Autowired
    private ProductOrderRepository productOrderRepository;

    @Autowired
    private ProductOrderMapper orderMapper;

    @Autowired
    private Validate validate;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PriceDetailRepository priceDetailRepository;

    @Autowired
    private UserRepository userRepository;

    public List<ProductOrderResponseDto> getAllProductOrders() {
        return productOrderRepository.findAll().stream().map(orderDetail -> orderMapper.convertEntityToDto(orderDetail)).collect(Collectors.toList());
    }

    public List<ProductOrderResponseDto> getUserProductOrders(String authorization) {
        return productOrderRepository.findAllByUserId(validate.getUserId(authorization)).stream().map(orderDetail -> orderMapper.convertEntityToDto(orderDetail)).collect(Collectors.toList());
    }

    public ProductOrderResponseDto addProductOrder(OrderFromProductRequestDto productOrderRequest, String authorization) {
        ProductOrder productOrderResponse = productOrderRepository.findByProductIdAndUserId(productOrderRequest.getProductId(), validate.getUserId(authorization)).orElse(null);
        if (Objects.isNull(productOrderResponse)) {
            Product product = productRepository.findById(productOrderRequest.getProductId()).orElse(null);
            PriceDetail priceDetail = priceDetailRepository.findFirstByProductId(productOrderRequest.getProductId()).orElse(null);
            if (Objects.nonNull(product) && Objects.nonNull(priceDetail)) {
                ProductOrder productOrder = orderMapper.convertDtoToEntity(productOrderRequest);
                return this.orderProduct(product, productOrder, priceDetail, authorization);
            } else {
                throw new GeneralException(ErrorConstants.PRICE_DETAIL_NOT_EXIST_ERROR);
            }
        } else {
            throw new GeneralException(ErrorConstants.PRODUCT_EXIST_ERROR);
        }
    }

    public ProductOrderResponseDto addProductFromPriceDetail(OrderFromPriceDetailRequestDto productOrderRequest, String authorization) {
        PriceDetail priceDetail = priceDetailRepository.findById(productOrderRequest.getPriceDetailId()).orElse(null);
        if (Objects.nonNull(priceDetail)) {
            int productId = priceDetail.getProduct().getId();
            Product product = productRepository.findById(productId).orElse(null);
            ProductOrder productOrderResponse = productOrderRepository.findByProductIdAndUserId(productId, validate.getUserId(authorization)).orElse(null);
            if (!Objects.nonNull(productOrderResponse) && Objects.nonNull(product)) {
                ProductOrder productOrder = orderMapper.convertDtoToEntity(productOrderRequest);
                return this.orderProduct(product, productOrder, priceDetail, authorization);
            } else {
                throw new GeneralException(ErrorConstants.PRODUCT_EXIST_ERROR);
            }
        } else {
            throw new GeneralException(ErrorConstants.PRICE_DETAIL_NOT_EXIST_ERROR);
        }
    }

    public ProductOrderResponseDto orderProduct(Product product, ProductOrder productOrder, PriceDetail priceDetail, String authorization) {
        if (product.getProductCount() > 0) {
            int productQuantity = productOrder.getQuantity();
            if (priceDetail.getProductCount() < productQuantity) {
                throw new RuntimeException("Only " + priceDetail.getProductCount() + " product available");
            }
            int count = product.getProductCount() - productQuantity;
            if (count <= 0) {
                product.setStatus(ProductStatus.OUT_OF_STOCK);
            }
            product.setProductCount(count);
            productRepository.save(product);
            priceDetail.setProductCount(priceDetail.getProductCount() - productQuantity);
            priceDetailRepository.save(priceDetail);
            long totalCost = productQuantity * (priceDetail.getSpecialOfferDiscount() + priceDetail.getDeliveryCharge() + priceDetail.getFinalPrice());
            productOrder.setProduct(product);
            productOrder.setCost(totalCost);
            productOrder.setUser(userRepository.findById(validate.getUserId(authorization)).get());
            return orderMapper.convertEntityToDto(productOrderRepository.save(productOrder));
        } else {
            throw new GeneralException(ErrorConstants.PRODUCT_AVAILABILITY_ERROR);
        }
    }

    public String removeProductOrder(int productOrderId) {
        productOrderRepository.deleteById(productOrderId);
        return "Successfully deleted Product order where id:" + productOrderId;
    }

}
