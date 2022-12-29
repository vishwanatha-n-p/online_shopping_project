package com.online.shopping.services;

import com.online.shopping.constants.ErrorConstants;
import com.online.shopping.entity.Product;
import com.online.shopping.entity.User;
import com.online.shopping.entity.WishList;
import com.online.shopping.exception.ProductNotFoundException;
import com.online.shopping.exception.WishListNotFoundException;
import com.online.shopping.general.Validate;
import com.online.shopping.mapper.WishListMapper;
import com.online.shopping.repository.ProductRepository;
import com.online.shopping.repository.UserRepository;
import com.online.shopping.repository.WishListRepository;
import com.online.shopping.requestdto.WishListRequestDto;
import com.online.shopping.responsedto.WishListResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WishListServices {

    @Autowired
    private WishListRepository wishListRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Validate validate;

    @Autowired
    private WishListMapper wishListMapper;

    public WishListResponseDto addToWishList(WishListRequestDto wishListRequestDto, String authorization) {
        int userId = validate.getUserId(authorization);
        Optional<Product> product = productRepository.findById(wishListRequestDto.getProductId());
        if (product.isPresent()) {
            Optional<WishList> wishList = wishListRepository.findByUserId(userId);
            if (!wishList.isPresent()) {
                WishList wishListResponse = wishListMapper.convertDtoToEntity(wishListRequestDto);
                Optional<User> user = userRepository.findById(userId);
                wishListResponse.setUser(user.get());
                wishListResponse.addProduct(product.get());
                return wishListMapper.convertEntityToDto(wishListRepository.save(wishListResponse));
            }
            List<Integer> idNumbers = wishListRepository.findAllProductIdByUserId(userId);
            if (!idNumbers.contains(wishListRequestDto.getProductId())) {
                wishList.get().addProduct(product.get());
                return wishListMapper.convertEntityToDto(wishListRepository.save(wishList.get()));
            }
            throw new ProductNotFoundException(ErrorConstants.PRODUCT_EXIST_ERROR);
        }
        throw new ProductNotFoundException(ErrorConstants.PRODUCT_NOT_EXIST_ERROR);
    }

    public WishListResponseDto getUserWishList(String authorization) {
        return wishListMapper.convertEntityToDto(wishListRepository.findByUserId(validate.getUserId(authorization)).get());
    }

    public List<WishListResponseDto> getAllWishList() {
        return wishListRepository.findAll().stream().map(wishList -> wishListMapper.convertEntityToDto(wishList)).collect(Collectors.toList());
    }

    public WishListResponseDto removeUserWishList(int productId, String authorization) {
        WishList wishList = wishListRepository.findByUserId(validate.getUserId(authorization)).orElse(null);
        if (Objects.nonNull(wishList)) {
            List<Product> products = wishList.getProducts().stream().filter(p -> !ObjectUtils.isEmpty(p) && p.getId() == productId).collect(Collectors.toList());
            wishList.removeProduct(products);
            return wishListMapper.convertEntityToDto(wishListRepository.save(wishList));
        }
        throw new WishListNotFoundException(ErrorConstants.WISHLIST_NOT_FOUND_ERROR);
    }

}
