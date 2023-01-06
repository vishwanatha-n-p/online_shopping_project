package com.online.shopping.unittestcases.services;

import com.online.shopping.requestdto.CustomerDetailRequestDto;
import com.online.shopping.responsedto.CustomerDetailResponseDto;
import com.online.shopping.services.CustomerDetailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CustomerDetailServiceTest {

    @Autowired
    private CustomerDetailService customerDetailService;

    @Test
    public void test_addCustomerDetail() {
        String securityToken = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJWaWthcyIsImV4cCI6MTY3MjIzMTg5OSwiaWF0IjoxNjcyMjEzODk5fQ.NgXGXP13mNLLkURlp6N0UZx740sinmjYyqaHD_TI1EmPgCf-QJUsuy8l2GVHykkVVBsJfdiXViOx3gzzDc6cEw";
        CustomerDetailRequestDto customerDetailRequest = new CustomerDetailRequestDto("Rahul", "Sankeshwar", "vishwanatha.vinno@gmail.com", "914359368578");
        CustomerDetailResponseDto customerDetailResponse = customerDetailService.addCustomerDetail(customerDetailRequest, securityToken);
        assertEquals(customerDetailRequest.getFirstName(), customerDetailResponse.getFirstName());
        assertEquals(customerDetailRequest.getLastName(), customerDetailResponse.getLastName());
        assertEquals(customerDetailRequest.getEmail(), customerDetailResponse.getEmail());
    }

    @Test
    public void test_getAllCustomerDetail() {
        List<CustomerDetailResponseDto> customerDetails = customerDetailService.getAllCustomerDetail();
        assertThat(customerDetails.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void test_getSingleCustomerDetail() {
        String securityToken = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJWaXJhdCIsImV4cCI6MTY3MjIyMzk0NSwiaWF0IjoxNjcyMjA1OTQ1fQ.0i3ftz3Gngh9Taj3NVzjAoo2H9kpJCuPj4a1SV5g4Tf17EAj7Z7W1GMKz2Bx2Y1H5ErXYf5D-HcqcYz3OO6YZg";
        CustomerDetailResponseDto customerDetailResponse = customerDetailService.getSingleCustomerDetail(securityToken);
        assertThat(customerDetailResponse).isNotNull();
    }

    @Test
    public void test_removeCustomerDetail() {
        int customerDetailId = 3;
        CustomerDetailResponseDto customerDetail = customerDetailService.removeCustomerDetail(customerDetailId);
        assertThat(customerDetail).isNotNull();
    }

}
