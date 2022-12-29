package com.online.shopping.services;

import com.online.shopping.constants.ErrorConstants;
import com.online.shopping.entity.Address;
import com.online.shopping.entity.CustomerDetail;
import com.online.shopping.exception.AddressNotFoundException;
import com.online.shopping.exception.CustomerDetailNotFoundException;
import com.online.shopping.general.Validate;
import com.online.shopping.mapper.AddressMapper;
import com.online.shopping.repository.AddressRepository;
import com.online.shopping.repository.CustomerDetailRepository;
import com.online.shopping.requestdto.AddressRequestDto;
import com.online.shopping.responsedto.AddressResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private CustomerDetailRepository customerDetailRepository;

    @Autowired
    private Validate validate;

    public AddressResponseDto addAddress(AddressRequestDto addressRequestDto, String authorization) {
        CustomerDetail customerDetail = customerDetailRepository.findLastCustomerDetailByUserId(validate.getUserId(authorization)).orElse(null);
        if (Objects.nonNull(customerDetail)) {
            Optional<Address> address = addressRepository.findByStreetAndCityAndState(addressRequestDto.getStreet(), addressRequestDto.getCity(), addressRequestDto.getState());
            if (!address.isPresent()) {
                Address addressRequest = addressMapper.convertDtoToEntity(addressRequestDto);
                Address addressResponse = addressRepository.save(addressRequest);
                customerDetail.addAddresses(addressResponse);
                customerDetailRepository.save(customerDetail);
                return addressMapper.convertEntityToDto(addressResponse);
            }
            customerDetail.addAddresses(address.get());
            customerDetailRepository.save(customerDetail);
            return addressMapper.convertEntityToDto(addressRepository.save(address.get()));
        }
        throw new CustomerDetailNotFoundException(ErrorConstants.CUSTOMER_DETAIL_NOT_EXIST_ERROR);
    }

    public List<AddressResponseDto> getAllAddress() {
        return addressRepository.findAll().stream().map(address -> addressMapper.convertEntityToDto(address)).collect(Collectors.toList());
    }

    public List<AddressResponseDto> getCustomerAddress(String authorization) {
        CustomerDetail customerDetail = customerDetailRepository.findLastCustomerDetailByUserId(validate.getUserId(authorization)).orElse(null);
        if (Objects.nonNull(customerDetail)) {
            List<Address> addressesResponse = customerDetail.retrieveAddresses();
            return addressesResponse.stream().map(address -> addressMapper.convertEntityToDto(address)).collect(Collectors.toList());
        }
        throw new CustomerDetailNotFoundException(ErrorConstants.CUSTOMER_DETAIL_NOT_EXIST_ERROR);
    }

    public AddressResponseDto getSingleAddress(int addressId) {
        Address address = addressRepository.findById(addressId).orElseThrow(() -> new AddressNotFoundException(ErrorConstants.ADDRESS_NOT_FOUND_ERROR + addressId));
        return addressMapper.convertEntityToDto(address);
    }

    public AddressResponseDto removeAddress(int addressId) {
        Address address = addressRepository.findById(addressId).orElseThrow(() -> new AddressNotFoundException(ErrorConstants.ADDRESS_NOT_FOUND_ERROR + addressId));
        addressRepository.delete(address);
        return addressMapper.convertEntityToDto(address);
    }

}
