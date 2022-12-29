package com.online.shopping.services;

import com.online.shopping.constants.ErrorConstants;
import com.online.shopping.entity.CartList;
import com.online.shopping.entity.Product;
import com.online.shopping.entity.User;
import com.online.shopping.exception.CartListNotFoundException;
import com.online.shopping.exception.ProductNotFoundException;
import com.online.shopping.general.Validate;
import com.online.shopping.mapper.CartListMapper;
import com.online.shopping.repository.CartListRepository;
import com.online.shopping.repository.ProductRepository;
import com.online.shopping.repository.UserRepository;
import com.online.shopping.requestdto.CartListRequestDto;
import com.online.shopping.responsedto.CartListResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartListService {

    @Autowired
    private CartListRepository cartListRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Validate validate;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartListMapper cartListMapper;

    public CartListResponseDto addToCartList(CartListRequestDto cartListRequestDto, String authorization) {
        int userId = validate.getUserId(authorization);
        Optional<Product> product = productRepository.findById(cartListRequestDto.getProductId());
        if (product.isPresent()) {
            CartList cartList = cartListRepository.findByUserId(userId).orElse(null);
            if (Objects.isNull(cartList)) {
                CartList cartListResponse = cartListMapper.convertDtoToEntity(cartListRequestDto);
                Optional<User> user = userRepository.findById(userId);
                cartListResponse.setUser(user.get());
                cartListResponse.addProduct(product.get());
                return cartListMapper.convertEntityToDto(cartListRepository.save(cartListResponse));
            }
            List<Integer> idNumbers = cartListRepository.findAllProductIdByUserId(userId);
            if (!idNumbers.contains(cartListRequestDto.getProductId())) {
                cartList.addProduct(product.get());
                return cartListMapper.convertEntityToDto(cartListRepository.save(cartList));
            }
            throw new ProductNotFoundException(ErrorConstants.PRODUCT_EXIST_ERROR);
        }
        throw new ProductNotFoundException(ErrorConstants.PRODUCT_NOT_EXIST_ERROR);
    }

    public CartListResponseDto getUserCartList(String authorization) {
        int userId = validate.getUserId(authorization);
        return cartListRepository.findByUserId(userId).map(cartList -> cartListMapper.convertEntityToDto(cartList)).get();
    }

    public List<CartListResponseDto> getAllCartList() {
        return cartListRepository.findAll().stream().map(cartList -> cartListMapper.convertEntityToDto(cartList)).collect(Collectors.toList());
    }

    public CartListResponseDto removeProductFromCartList(int productId, String authorization) {
        CartList cartList = cartListRepository.findByUserId(validate.getUserId(authorization)).orElse(null);
        if (Objects.nonNull(cartList)) {
            List<Product> products = cartList.getProducts().stream().filter(p -> !ObjectUtils.isEmpty(p) && p.getId() == productId).collect(Collectors.toList());
            cartList.removeProduct(products);
            return cartListMapper.convertEntityToDto(cartListRepository.save(cartList));
        }
        throw new CartListNotFoundException(ErrorConstants.CART_LIST_NOT_FOUND_ERROR);
    }

}
