package com.online.shopping.unittestcases.services;

import com.online.shopping.requestdto.AddressRequestDto;
import com.online.shopping.responsedto.AddressResponseDto;
import com.online.shopping.services.AddressService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AddressServiceTest {

    @Autowired
    private AddressService addressService;

    @Test
    public void test_addAddress() {
        String securityToken = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJWaWthcyIsImV4cCI6MTY3MjI0NDMzMSwiaWF0IjoxNjcyMjI2MzMxfQ.XmRnLoqfhXaeQCdWOgPR78FuvR0uJXmoCEBltYU4tkojcDPlG60v1qvfuE8RgdXqzzKVbLQ2aqN-u4aBVk_LTg";
        AddressRequestDto addressRequest = new AddressRequestDto("Ramanahalli road 2nd cross", "Hubli", "Karnataka", "580021", "India");
        AddressResponseDto addressResponse = addressService.addAddress(addressRequest, securityToken);
        assertEquals(addressRequest.getStreet(), addressResponse.getStreet());
        assertEquals(addressRequest.getCity(), addressResponse.getCity());
        assertEquals(addressRequest.getState(), addressResponse.getState());
        assertEquals(addressRequest.getPostalCode(), addressResponse.getPostalCode());
        assertEquals(addressRequest.getCountry(), addressResponse.getCountry());
    }

    @Test
    public void test_getAllAddress() {
        List<AddressResponseDto> addresses = addressService.getAllAddress();
        assertThat(addresses.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void test_getCustomerAddress() {
        String securityToken = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJWaXJhdCIsImV4cCI6MTY3MjIyMzk0NSwiaWF0IjoxNjcyMjA1OTQ1fQ.0i3ftz3Gngh9Taj3NVzjAoo2H9kpJCuPj4a1SV5g4Tf17EAj7Z7W1GMKz2Bx2Y1H5ErXYf5D-HcqcYz3OO6YZg";
        List<AddressResponseDto> addresses = addressService.getCustomerAddress(securityToken);
        assertThat(addresses.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void test_getSingleAddress() {
        int addressId = 2;
        AddressResponseDto addressResponse = addressService.getSingleAddress(addressId);
        assertThat(addressResponse).isNotNull();
    }

    @Test
    public void test_removeAddress() {
        int addressId = 4;
        AddressResponseDto addressResponse = addressService.removeAddress(addressId);
        assertThat(addressResponse).isNotNull();
    }

}
