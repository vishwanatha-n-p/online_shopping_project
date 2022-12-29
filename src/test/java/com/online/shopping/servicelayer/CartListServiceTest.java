package com.online.shopping.servicelayer;

import com.online.shopping.requestdto.CartListRequestDto;
import com.online.shopping.responsedto.CartListResponseDto;
import com.online.shopping.services.CartListService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CartListServiceTest {

    @Autowired
    private CartListService cartListService;

    @Test
    public void test_addToCartList() {
        String securityToken = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJWaWthcyIsImV4cCI6MTY3MjI0NDMzMSwiaWF0IjoxNjcyMjI2MzMxfQ.XmRnLoqfhXaeQCdWOgPR78FuvR0uJXmoCEBltYU4tkojcDPlG60v1qvfuE8RgdXqzzKVbLQ2aqN-u4aBVk_LTg";
        CartListRequestDto cartListRequest = new CartListRequestDto(10);
        CartListResponseDto cartListResponse = cartListService.addToCartList(cartListRequest, securityToken);
        assertThat(cartListResponse).isNotNull();
    }

    @Test
    public void test_getAllCartList() {
        List<CartListResponseDto> cartLists = cartListService.getAllCartList();
        assertThat(cartLists.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void test_getUserCartList() {
        String authorization = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJWaWthcyIsImV4cCI6MTY3MjI0NDMzMSwiaWF0IjoxNjcyMjI2MzMxfQ.XmRnLoqfhXaeQCdWOgPR78FuvR0uJXmoCEBltYU4tkojcDPlG60v1qvfuE8RgdXqzzKVbLQ2aqN-u4aBVk_LTg";
        CartListResponseDto cartListResponse = cartListService.getUserCartList(authorization);
        assertThat(cartListResponse.getProducts().size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void test_removeProductFromCartList() {
        int productId = 10;
        String authorization = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJWaWthcyIsImV4cCI6MTY3MjI0NDMzMSwiaWF0IjoxNjcyMjI2MzMxfQ.XmRnLoqfhXaeQCdWOgPR78FuvR0uJXmoCEBltYU4tkojcDPlG60v1qvfuE8RgdXqzzKVbLQ2aqN-u4aBVk_LTg";
        CartListResponseDto cartListResponse = cartListService.removeProductFromCartList(productId, authorization);
        assertThat(cartListResponse).isNotNull();
    }

}
