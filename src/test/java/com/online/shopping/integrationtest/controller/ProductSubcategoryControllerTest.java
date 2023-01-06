package com.online.shopping.integrationtest.controller;

import com.online.shopping.OnlineShoppingApplication;
import com.online.shopping.requestdto.ProductSubcategoryRequestDto;
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
public class ProductSubcategoryControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private HeadersCreation headers;

    @Autowired
    private UrlCreation urlWithPort;

    @Test
    public void testAddSubcategory() throws Exception {
        UserRequestDto userRequest = new UserRequestDto("Manager", "Manager123", "Manager");
        String uriToAddSubcategory = "/subcategories";
        ProductSubcategoryRequestDto subcategoryRequest = new ProductSubcategoryRequestDto("Kitchen, Cookware & Serveware", 3);
        HttpEntity<ProductSubcategoryRequestDto> entity = new HttpEntity<ProductSubcategoryRequestDto>(subcategoryRequest, headers.obtainHeaders(userRequest));
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(urlWithPort.formUrlWithPort(uriToAddSubcategory, port), HttpMethod.POST, entity, String.class);
        assertThat(responseEntity.getBody()).isNotNull();
    }

    @Test
    public void testGetAllSubcategory() throws Exception {
        UserRequestDto userRequest = new UserRequestDto("Ramadas", "Ramadas123", "Customer");
        String uriTOGetAllSubcategory = "/subcategories";
        HttpEntity<String> entity = new HttpEntity<String>(null, headers.obtainHeaders(userRequest));
        ResponseEntity<List> responseEntity = testRestTemplate.exchange(urlWithPort.formUrlWithPort(uriTOGetAllSubcategory, port), HttpMethod.GET, entity, List.class);
        System.out.println();
        System.out.println(responseEntity.getBody());
        System.out.println(responseEntity.getBody().size());
        System.out.println();
        assertThat(responseEntity.getBody().size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void testGetSingleSubcategory() throws Exception {
        UserRequestDto userRequest = new UserRequestDto("Ramadas", "Ramadas123", "Customer");
        String uriToGetSingleSubcategory = "/subcategories/3";
        HttpEntity<String> entity = new HttpEntity<String>(null, headers.obtainHeaders(userRequest));
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(urlWithPort.formUrlWithPort(uriToGetSingleSubcategory, port), HttpMethod.GET, entity, String.class);
        assertThat(responseEntity.getBody()).isNotNull();
    }

    @Test
    public void testActivateSubcategory() throws Exception {
        UserRequestDto userRequest = new UserRequestDto("Manager", "Manager123", "Manager");
        String uriToActivateSubcategory = "/subcategories/activateStatus/4";
        HttpEntity<String> entity = new HttpEntity<String>(null, headers.obtainHeaders(userRequest));
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(urlWithPort.formUrlWithPort(uriToActivateSubcategory, port), HttpMethod.PUT, entity, String.class);
        assertThat(responseEntity.getBody()).isNotNull();
    }

    @Test
    public void testInactivateSubcategory() throws Exception {
        UserRequestDto userRequest = new UserRequestDto("Manager", "Manager123", "Manager");
        String uriToInactivateSubcategory = "/subcategories/inactivateStatus/6";
        HttpEntity<String> entity = new HttpEntity<String>(null, headers.obtainHeaders(userRequest));
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(urlWithPort.formUrlWithPort(uriToInactivateSubcategory, port), HttpMethod.PUT, entity, String.class);
        assertThat(responseEntity.getBody()).isNotNull();
    }

    @Test
    public void testRemoveSubcategory() throws Exception {
        UserRequestDto userRequest = new UserRequestDto("Manager", "Manager123", "Manager");
        String uriToRemoveSubcategory = "/subcategories/11";
        HttpEntity<String> entity = new HttpEntity<String>(null, headers.obtainHeaders(userRequest));
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(urlWithPort.formUrlWithPort(uriToRemoveSubcategory, port), HttpMethod.DELETE, entity, String.class);
        assertThat(responseEntity.getBody()).isNotNull();
    }

    @Test
    public void testGetSubcategoriesOfCategory() throws Exception {
        UserRequestDto userRequest = new UserRequestDto("Ramadas", "Ramadas123", "Customer");
        String uriTOGetSubcategoriesOfCategory = "/subcategories/categories/2";
        HttpEntity<String> entity = new HttpEntity<String>(null, headers.obtainHeaders(userRequest));
        ResponseEntity<List> responseEntity = testRestTemplate.exchange(urlWithPort.formUrlWithPort(uriTOGetSubcategoriesOfCategory, port), HttpMethod.GET, entity, List.class);
        System.out.println();
        System.out.println(responseEntity.getBody());
        System.out.println(responseEntity.getBody().size());
        System.out.println();
        assertThat(responseEntity.getBody().size()).isGreaterThanOrEqualTo(1);
    }

}
