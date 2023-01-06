package com.online.shopping.integrationtest.controller;

import com.online.shopping.OnlineShoppingApplication;
import com.online.shopping.requestdto.ProductCategoryRequestDto;
import com.online.shopping.requestdto.UserRequestDto;
import com.online.shopping.utility.HeadersCreation;
import com.online.shopping.utility.UrlCreation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = OnlineShoppingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductCategoryControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private HeadersCreation headers;

    @Autowired
    private UrlCreation urlWithPort;

    @Test
    public void testAddCategory() throws Exception {
        UserRequestDto userRequest = new UserRequestDto("Manager", "Manager123", "Manager");
        String uriToAddCategory = "/api/categories";
        ProductCategoryRequestDto productCategoryRequest = new ProductCategoryRequestDto("Some");
        HttpEntity<ProductCategoryRequestDto> entity = new HttpEntity<ProductCategoryRequestDto>(productCategoryRequest, headers.obtainHeaders(userRequest));
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(urlWithPort.formUrlWithPort(uriToAddCategory, port), HttpMethod.POST, entity, String.class);
        assertThat(responseEntity.getBody()).isNotNull();
    }

    @Test
    public void testGetAllCategory() throws Exception {
        UserRequestDto userRequest = new UserRequestDto("Ramadas", "Ramadas123", "Customer");
        String uriToGetAllCategory = "/api/categories";
        HttpEntity<String> entity = new HttpEntity<String>(null, headers.obtainHeaders(userRequest));
        ResponseEntity<List> productCategories = testRestTemplate.exchange(urlWithPort.formUrlWithPort(uriToGetAllCategory, port), HttpMethod.GET, entity, List.class);
        assertThat(productCategories.getBody().size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void testGetSingleCategory() throws Exception {
        UserRequestDto userRequest = new UserRequestDto("Ramadas", "Ramadas123", "Customer");
        String uriToGetSingleCategory = "/api/categories/3";
        HttpEntity<String> entity = new HttpEntity<String>(null, headers.obtainHeaders(userRequest));
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(urlWithPort.formUrlWithPort(uriToGetSingleCategory, port), HttpMethod.GET, entity, String.class);
        assertThat(responseEntity.getBody()).isNotNull();
    }

    @Test
    public void testInactivateCategory() throws Exception {
        UserRequestDto userRequest = new UserRequestDto("Manager", "Manager123", "Manager");
        String uriToInactivateCategory = "/api/categories/inactiveStatus/2";
        HttpEntity<String> entity = new HttpEntity<String>(null, headers.obtainHeaders(userRequest));
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(urlWithPort.formUrlWithPort(uriToInactivateCategory, port), HttpMethod.PUT, entity, String.class);
        assertThat(responseEntity.getBody()).isNotNull();
    }

    @Test
    public void testActivateCategory() throws Exception {
        UserRequestDto userRequest = new UserRequestDto("Manager", "Manager123", "Manager");
        String uriToActivateCategory = "/api/categories/activeStatus/5";
        HttpEntity<String> entity = new HttpEntity<String>(null, headers.obtainHeaders(userRequest));
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(urlWithPort.formUrlWithPort(uriToActivateCategory, port), HttpMethod.PUT, entity, String.class);
        assertThat(responseEntity.getBody()).isNotNull();
    }

    @Test
    public void testRemoveCategory() throws Exception {
        UserRequestDto userRequest = new UserRequestDto("Manager", "Manager123", "Manager");
        String uriToRemoveCategory = "/api/categories/14";
        HttpEntity<String> entity = new HttpEntity<String>(null, headers.obtainHeaders(userRequest));
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(urlWithPort.formUrlWithPort(uriToRemoveCategory, port), HttpMethod.DELETE, entity, String.class);
        assertThat(responseEntity.getBody()).isNotNull();
    }

}
