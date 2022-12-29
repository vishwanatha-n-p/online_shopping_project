package com.online.shopping.servicelayer;

import com.online.shopping.requestdto.WishListRequestDto;
import com.online.shopping.responsedto.WishListResponseDto;
import com.online.shopping.services.WishListServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class WishListServiceTest {

    @Autowired
    private WishListServices wishListServices;

    @Test
    public void test_addToWishList() {
        String securityToken = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJWaXJhdCIsImV4cCI6MTY3MjA2ODI4OSwiaWF0IjoxNjcyMDUwMjg5fQ.sZuU4NGmmkfWiRUjyuzEKQcvo_wF036Ioa941Xwkcy_BLmfT1qi1XdwLIeo5rbI22Tx1mdX5Q6Jkg0LPtLiyZg";
        WishListRequestDto wishListRequest = new WishListRequestDto(10);
        WishListResponseDto wishListResponse = wishListServices.addToWishList(wishListRequest, securityToken);
        assertThat(wishListResponse).isNotNull();
    }

    @Test
    public void test_getAllWishList() {
        List<WishListResponseDto> wishLists = wishListServices.getAllWishList();
        assertThat(wishLists.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void test_getUserWishList() {
        String securityToken = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJWaWthcyIsImV4cCI6MTY3MjA2NjI2OSwiaWF0IjoxNjcyMDQ4MjY5fQ.GmtzHuhFMVn9p114WwEz9ZpyhsxBNUXpJo21_PB1pZGifk53e1KJDgez_Hd5HIUNqS4Gwh15kkb8eFo335p1sA";
        WishListResponseDto wishListResponse = wishListServices.getUserWishList(securityToken);
        assertThat(wishListResponse.getProducts().size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void test_removeUserWishList() {
        int productId = 8;
        String securityToken = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJWaWthcyIsImV4cCI6MTY3MjA2NjI2OSwiaWF0IjoxNjcyMDQ4MjY5fQ.GmtzHuhFMVn9p114WwEz9ZpyhsxBNUXpJo21_PB1pZGifk53e1KJDgez_Hd5HIUNqS4Gwh15kkb8eFo335p1sA";
        WishListResponseDto wishListResponse = wishListServices.removeUserWishList(productId, securityToken);
        assertThat(wishListResponse).isNotNull();
    }

}
