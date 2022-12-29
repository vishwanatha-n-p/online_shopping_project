package com.online.shopping.services;

import com.online.shopping.constants.ErrorConstants;
import com.online.shopping.entity.Seller;
import com.online.shopping.entity.User;
import com.online.shopping.exception.SellerNotFoundException;
import com.online.shopping.exception.UserNotFoundException;
import com.online.shopping.general.Validate;
import com.online.shopping.mapper.SellerMapper;
import com.online.shopping.repository.SellerRepository;
import com.online.shopping.repository.UserRepository;
import com.online.shopping.requestdto.SellerRequestDto;
import com.online.shopping.responsedto.SellerResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private SellerMapper sellerMapper;

    @Autowired
    private Validate validate;

    @Autowired
    private UserRepository userRepository;

    public List<SellerResponseDto> getAllSeller() {
        return sellerRepository.findAll().stream().map(seller -> sellerMapper.convertEntityToDto(seller)).collect(Collectors.toList());
    }

    public SellerResponseDto getSingleSeller(int sellerId) {
        Seller seller = sellerRepository.findById(sellerId).orElseThrow(() -> new SellerNotFoundException("Seller not found for id: " + sellerId));
        return sellerMapper.convertEntityToDto(seller);
    }

    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) {
        Optional<Seller> sellerRequest = sellerRepository.findById(sellerRequestDto.getId());
        if (!sellerRequest.isPresent()) {
            Seller sellerResponse = sellerMapper.convertDtoToEntity(sellerRequestDto);
            return sellerMapper.convertEntityToDto(sellerRepository.save(sellerResponse));
        }
        throw new SellerNotFoundException(ErrorConstants.SELLER_EXIST_ERROR);
    }

    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto, String authorization) {
        User user = userRepository.findById(validate.getUserId(authorization)).orElse(null);
        Optional<Seller> sellerRequest = sellerRepository.findById(sellerRequestDto.getId());
        if (!sellerRequest.isPresent()) {
            Seller sellerResponse = sellerMapper.convertDtoToEntity(sellerRequestDto);
            if (Objects.nonNull(user)) {
                sellerResponse.setUser(user);
            } else {
                throw new UserNotFoundException(ErrorConstants.USER_NOT_FOUND_ERROR);
            }
            return sellerMapper.convertEntityToDto(sellerRepository.save(sellerResponse));
        }
        throw new SellerNotFoundException(ErrorConstants.SELLER_EXIST_ERROR);
    }

    public SellerResponseDto removeSeller(int sellerId) {
        Seller seller = sellerRepository.findById(sellerId).orElseThrow(() -> new SellerNotFoundException(ErrorConstants.SELLER_NOT_FOUND_ERROR + sellerId));
        sellerRepository.delete(seller);
        return sellerMapper.convertEntityToDto(seller);
    }

}
