package com.online.shopping.mapper;

import com.online.shopping.entity.Address;
import com.online.shopping.requestdto.AddressRequestDto;
import com.online.shopping.responsedto.AddressResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    @Autowired
    private ModelMapper mapper;

    public Address convertDtoToEntity(AddressRequestDto addressRequestDto) {
        return new Address(addressRequestDto.getStreet(), addressRequestDto.getCity(), addressRequestDto.getState(), addressRequestDto.getPostalCode(), addressRequestDto.getCountry());
    }

    public AddressResponseDto convertEntityToDto(Address address) {
        return mapper.map(address, AddressResponseDto.class);
    }

}
