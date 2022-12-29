package com.online.shopping.services;

import com.online.shopping.constants.ErrorConstants;
import com.online.shopping.entity.CustomerDetail;
import com.online.shopping.entity.FinalOrder;
import com.online.shopping.entity.Payment;
import com.online.shopping.entity.PaymentMode;
import com.online.shopping.entity.ProductOrder;
import com.online.shopping.enums.PaymentStatus;
import com.online.shopping.exception.GeneralException;
import com.online.shopping.general.Validate;
import com.online.shopping.mapper.PaymentMapper;
import com.online.shopping.repository.CustomerDetailRepository;
import com.online.shopping.repository.FinalOrderRepository;
import com.online.shopping.repository.PaymentModeRepository;
import com.online.shopping.repository.PaymentRepository;
import com.online.shopping.repository.ProductOrderRepository;
import com.online.shopping.requestdto.PaymentRequestDto;
import com.online.shopping.responsedto.PaymentResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentModeRepository paymentModeRepository;

    @Autowired
    private ProductOrderRepository productOrderRepository;

    @Autowired
    private CustomerDetailRepository customerDetailRepository;

    @Autowired
    private Validate validate;

    @Autowired
    private PaymentMapper paymentMapper;

    @Autowired
    private FinalOrderService finalOrderService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private FinalOrderRepository finalOrderRepository;

    public PaymentResponseDto addPayment(PaymentRequestDto paymentRequestDto, String authorization) {
        Payment payment = paymentMapper.createPaymentEntity(paymentRequestDto);
        Optional<PaymentMode> paymentMode = paymentModeRepository.findByModeName(paymentRequestDto.getPaymentModeName());
        int userId = validate.getUserId(authorization);
        paymentMode.ifPresent(pm -> payment.setPaymentMode(pm));
        long totalAmount = productOrderRepository.findCostSumByUserId(userId);
        payment.setAmount(totalAmount);
        CustomerDetail customerDetail = customerDetailRepository.findLastCustomerDetailByUserId(userId).orElse(null);
        if (Objects.isNull(customerDetail)) {
            throw new GeneralException(ErrorConstants.CUSTOMER_DETAIL_NOT_EXIST_ERROR);
        }
        payment.setCustomerDetail(customerDetail);

        if (paymentRequestDto.getPaymentModeName().equals("Cash on Delivery")) {
            payment.setPaymentStatus(PaymentStatus.NOT_PAID);
        } else {
            payment.setPaymentDate(LocalDateTime.now());
            payment.setPaymentStatus(PaymentStatus.PAID);
        }
        Payment paymentResponse = paymentRepository.save(payment);
        List<ProductOrder> productOrders = productOrderRepository.findAllByUserId(userId);
        finalOrderService.saveFinalOrder(productOrders, paymentResponse);
        sendSimpleConfirmationMail(customerDetail);
        sendOrderConfirmationMail(customerDetail, paymentResponse.getId());
        productOrders.stream().forEach(productOrder -> productOrderRepository.deleteById(productOrder.getId()));
        return paymentMapper.convertEntityToDto(paymentResponse);
    }

    private void sendSimpleConfirmationMail(CustomerDetail customerDetail) {
        String to = customerDetail.getEmail();
        String subject = "Order Confirmation";
        String text = "Dear " + customerDetail.getFirstName() + " " + customerDetail.getLastName() + ",\nYour order is Confirmed!" +
                "\nThank you for shopping in Flipkart, Your order is placed and Order will be delivered to you on time.\n\nHave a great day.";
        emailService.sendSimpleMessage(to, subject, text);
    }

    private void sendOrderConfirmationMail(CustomerDetail customerDetail, int paymentId) {
        String to = customerDetail.getEmail();
        String subject = "Order Confirmation";
        String text;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Dear " + customerDetail.getFirstName() + " " + customerDetail.getLastName() + ",<br>Your order is Confirmed!<br>" +
                "Thank you for shopping in Flipkart, Your order is placed and Order will be delivered to you on time.<br>" +
                "<html><body><h3>Ordered Products</h3><form><table style=\"border: 1px solid black;padding:10px;border-collapse: collapse;text-align:center;\">" +
                "<tr style=\"border: 1px solid black;padding:10px;\"><th style=\"border: 1px solid black;padding:10px;\">Order Number</th>" +
                "<th style=\"border: 1px solid black;padding:10px;\">Product</th><th style=\"border: 1px solid black;padding:10px;\">Quantity</th>" +
                "<th style=\"border: 1px solid black;padding:10px;\">Cost</th><th style=\"border: 1px solid black;padding:10px;\">Delivery Date</th></tr>");

        List<FinalOrder> finalOrders = finalOrderRepository.findAllByPaymentId(paymentId);
        long sum = 0;
        for (FinalOrder finalOrder : finalOrders) {
            stringBuilder.append("<tr style=\"border: 1px solid black;padding:10px;\"><td style=\"border: 1px solid black;padding:10px;\">" + finalOrder.getId() +
                    "</td><td style=\"border: 1px solid black;padding:10px;\">" + finalOrder.getProduct().getProductName() +
                    "</td><td style=\"border: 1px solid black;padding:10px;\">" + finalOrder.getQuantity() +
                    "</td><td style=\"border: 1px solid black;padding:10px;\">" + finalOrder.getCost() +
                    "</td><td style=\"border: 1px solid black;padding:10px;\">" + finalOrder.getDeliveryDate() + "</td></tr>");
            sum += finalOrder.getCost();
        }
        stringBuilder.append("</table><h4>Total cost : " + sum + "</h4></form></body></html><br>Have a great day.");
        text = stringBuilder.toString();
        emailService.sendMailWithAttachment(to, subject, text);
    }

    public List<PaymentResponseDto> getAllPayments() {
        return paymentRepository.findAll().stream().map(payment -> paymentMapper.convertEntityToDto(payment)).collect(Collectors.toList());
    }

    public PaymentResponseDto getSinglePayment(int paymentId) {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new GeneralException(ErrorConstants.PAYMENT_NOT_FOUND_ERROR + paymentId));
        return paymentMapper.convertEntityToDto(payment);
    }

    public PaymentResponseDto removePayment(int paymentId) {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new GeneralException(ErrorConstants.PAYMENT_NOT_FOUND_ERROR + paymentId));
        try {
            paymentRepository.delete(payment);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(ErrorConstants.PAYMENT_ALREADY_USED_ERROR);
        }
        return paymentMapper.convertEntityToDto(payment);
    }

}

