package com.online.shopping.unittestcases.services;

import com.online.shopping.requestdto.SellerRequestDto;
import com.online.shopping.responsedto.SellerResponseDto;
import com.online.shopping.services.SellerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class SellerServiceTest {

    @Autowired
    private SellerService sellerService;

    @Test
    public void test_addSeller() {
        String securityToken = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJUQkwgT25saW5lIiwiZXhwIjoxNjcxNjE3OTYxLCJpYXQiOjE2NzE1OTk5NjF9.-Fk4P8BaI7zkMZian3TorPhRb8XHPagBrkP4hoymsYB1l7ZevKh6cER2qNhug9_mRYCGzUdx0ry5Tdv91IjrkQ";
        SellerRequestDto sellerRequest = new SellerRequestDto("TBL Online", "2 years 8 months", "917923335456", "tblonline@gmail.com", "Hyderabad", "Telangana", "500007", "India");
        SellerResponseDto sellerResponse = sellerService.addSeller(sellerRequest, securityToken);
        assertEquals(sellerRequest.getSellerName(), sellerResponse.getSellerName());
        assertEquals(sellerRequest.getService(), sellerResponse.getService());
        assertEquals(sellerRequest.getContactNumber(), sellerResponse.getContactNumber());
    }

    @Test
    public void test_getAllSeller() {
        List<SellerResponseDto> sellers = sellerService.getAllSeller();
        assertThat(sellers.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void test_getSingleSeller() {
        int sellerId = 2;
        SellerResponseDto sellerResponse = sellerService.getSingleSeller(sellerId);
        assertEquals(sellerId, sellerResponse.getId());
    }

    @Test
    public void test_removeSeller() {
        int sellerId = 3;
        SellerResponseDto sellerResponse = sellerService.removeSeller(sellerId);
        assertEquals(sellerId, sellerResponse.getId());
    }

}
