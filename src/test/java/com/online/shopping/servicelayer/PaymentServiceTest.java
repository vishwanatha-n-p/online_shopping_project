package com.online.shopping.servicelayer;

import com.online.shopping.requestdto.PaymentRequestDto;
import com.online.shopping.responsedto.PaymentResponseDto;
import com.online.shopping.services.PaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;

    @Test
    public void test_addPayment() {
        String securityToken = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJWaWthcyIsImV4cCI6MTY3MjIzMTg5OSwiaWF0IjoxNjcyMjEzODk5fQ.NgXGXP13mNLLkURlp6N0UZx740sinmjYyqaHD_TI1EmPgCf-QJUsuy8l2GVHykkVVBsJfdiXViOx3gzzDc6cEw";
        PaymentRequestDto paymentRequest = new PaymentRequestDto("Cash on Delivery");
        PaymentResponseDto paymentResponse = paymentService.addPayment(paymentRequest, securityToken);
        assertEquals(paymentRequest.getPaymentModeName(), paymentResponse.getPaymentMode().getModeName());
    }

    @Test
    public void test_getAllPayments() {
        List<PaymentResponseDto> payments = paymentService.getAllPayments();
        assertThat(payments.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void test_getSinglePayment() {
        int paymentId = 2;
        PaymentResponseDto paymentResponse = paymentService.getSinglePayment(paymentId);
        assertThat(paymentResponse).isNotNull();
    }

    @Test
    public void test_removePayment() {
        int paymentId = 2;
        PaymentResponseDto payment = paymentService.removePayment(paymentId);
        assertThat(payment).isNotNull();
    }

}
