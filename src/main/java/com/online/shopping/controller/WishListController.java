package com.online.shopping.controller;

import com.online.shopping.requestdto.WishListRequestDto;
import com.online.shopping.responsedto.WishListResponseDto;
import com.online.shopping.services.WishListServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/wishlists")
public class WishListController {

    @Autowired
    private WishListServices wishListServices;

    @PreAuthorize("hasRole('ROLE_Customer')")
    @PostMapping
    public WishListResponseDto addToWishList(@Valid @RequestBody WishListRequestDto wishListRequestDto, @RequestHeader String authorization) {
        return wishListServices.addToWishList(wishListRequestDto, authorization);
    }

    @PreAuthorize("hasRole('ROLE_Customer')")
    @GetMapping("/user")
    public WishListResponseDto getUserWishList(@RequestHeader String authorization) {
        return wishListServices.getUserWishList(authorization);
    }

    @PreAuthorize("hasRole('ROLE_Manager')")
    @GetMapping
    public List<WishListResponseDto> getAllWishList() {
        return wishListServices.getAllWishList();
    }

    @PreAuthorize("hasRole('ROLE_Customer')")
    @DeleteMapping("/{productId}")
    public WishListResponseDto removeWishList(@PathVariable int productId, @RequestHeader String authorization) {
        return wishListServices.removeUserWishList(productId, authorization);
    }

}
