package com.online.shopping.controller;

import com.online.shopping.requestdto.CartListRequestDto;
import com.online.shopping.responsedto.CartListResponseDto;
import com.online.shopping.services.CartListService;
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
@RequestMapping("/cartLists")
public class CartListController {

    @Autowired
    private CartListService cartListService;

    @PreAuthorize("hasRole('ROLE_Customer')")
    @PostMapping
    public CartListResponseDto addToCartList(@Valid @RequestBody CartListRequestDto cartListRequestDto, @RequestHeader String authorization) {
        return cartListService.addToCartList(cartListRequestDto, authorization);
    }

    @PreAuthorize("hasRole('ROLE_Customer')")
    @GetMapping("/user")
    public CartListResponseDto getUserCartList(@RequestHeader String authorization) {
        return cartListService.getUserCartList(authorization);
    }

    @PreAuthorize("hasRole('ROLE_Manager')")
    @GetMapping
    public List<CartListResponseDto> getAllCartList() {
        return cartListService.getAllCartList();
    }

    @PreAuthorize("hasRole('ROLE_Customer')")
    @DeleteMapping("/{productId}")
    public CartListResponseDto removeProductFromCartList(@PathVariable int productId, @RequestHeader String authorization) {
        return cartListService.removeProductFromCartList(productId, authorization);
    }

}
