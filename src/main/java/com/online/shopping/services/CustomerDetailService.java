package com.online.shopping.services;

import com.online.shopping.constants.ErrorConstants;
import com.online.shopping.entity.CustomerDetail;
import com.online.shopping.entity.User;
import com.online.shopping.exception.CustomerDetailNotFoundException;
import com.online.shopping.exception.GeneralException;
import com.online.shopping.exception.UserNotFoundException;
import com.online.shopping.general.Validate;
import com.online.shopping.mapper.CustomerDetailMapper;
import com.online.shopping.repository.CustomerDetailRepository;
import com.online.shopping.repository.UserRepository;
import com.online.shopping.requestdto.CustomerDetailRequestDto;
import com.online.shopping.responsedto.CustomerDetailResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CustomerDetailService {

    @Autowired
    private CustomerDetailRepository customerDetailRepository;

    @Autowired
    private CustomerDetailMapper customerDetailMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Validate validate;

    public List<CustomerDetailResponseDto> getAllCustomerDetail() {
        return customerDetailRepository.findAll().stream().map(customerDetail -> customerDetailMapper.convertEntityToDto(customerDetail)).collect(Collectors.toList());
    }

    public CustomerDetailResponseDto getSingleCustomerDetail(String authorization) {
        CustomerDetail customerDetail = customerDetailRepository.findLastCustomerDetailByUserId(validate.getUserId(authorization)).orElseThrow(() -> new CustomerDetailNotFoundException(ErrorConstants.CUSTOMER_DETAIL_NOT_EXIST_ERROR));
        return customerDetailMapper.convertEntityToDto(customerDetail);
    }

    public CustomerDetailResponseDto addCustomerDetail(CustomerDetailRequestDto customerDetailRequest, String authorization) {
        User user = userRepository.findById(validate.getUserId(authorization)).orElse(null);
        if (Objects.nonNull(user)) {
            CustomerDetail customerDetail = customerDetailRepository.findCustomerDetail(user.getId(), customerDetailRequest.getFirstName(), customerDetailRequest.getLastName(), customerDetailRequest.getEmail()).orElse(null);
            if (Objects.nonNull(customerDetail)) {
                throw new GeneralException(ErrorConstants.CUSTOMER_DETAIL_EXIST_ERROR);
            }
            CustomerDetail customerDetailResponse = customerDetailMapper.convertDtoToEntity(customerDetailRequest);
            customerDetailResponse.setUser(user);
            return customerDetailMapper.convertEntityToDto(customerDetailRepository.save(customerDetailResponse));
        }
        throw new UserNotFoundException(ErrorConstants.USER_NOT_EXIST_ERROR);
    }

    public CustomerDetailResponseDto removeCustomerDetail(int customerDetailId) {
        CustomerDetail customerDetail = customerDetailRepository.findById(customerDetailId).orElseThrow(() -> new CustomerDetailNotFoundException(ErrorConstants.CUSTOMER_DETAIL_NOT_FOUND_ERROR + customerDetailId));
        customerDetailRepository.delete(customerDetail);
        return customerDetailMapper.convertEntityToDto(customerDetail);
    }

}
