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
            List<Integer> idNumbers = cartListRepository.findAllProductIdByUserId(userId);
            Optional<CartList> cartList = cartListRepository.findByUserId(userId);
            Optional<User> user = userRepository.findById(userId);
            if (!cartList.isPresent()) {
                CartList cartListResponse = cartListMapper.convertDtoToEntity(cartListRequestDto);
                cartListResponse.setUser(user.get());
                cartListResponse.addProduct(product.get());
                return cartListMapper.convertEntityToDto(cartListRepository.save(cartListResponse));
            }
            if (cartList.isPresent() && !idNumbers.contains(cartListRequestDto.getProductId())) {
                cartList.get().addProduct(product.get());
                return cartListMapper.convertEntityToDto(cartListRepository.save(cartList.get()));
            } else {
                throw new ProductNotFoundException(ErrorConstants.PRODUCT_EXIST_ERROR);
            }
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
        Optional<CartList> cartList = cartListRepository.findByUserId(validate.getUserId(authorization));
        if (cartList.isPresent()) {
            cartList.get().getProducts().stream().filter(p -> !ObjectUtils.isEmpty(p) && p.getId() == productId).forEach(a -> cartList.get().removeProduct(a));
            return cartListMapper.convertEntityToDto(cartListRepository.save(cartList.get()));
        }
        throw new CartListNotFoundException(ErrorConstants.CART_LIST_NOT_FOUND_ERROR);
    }

}
