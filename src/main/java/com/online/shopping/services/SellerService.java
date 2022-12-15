package com.online.shopping.services;

import com.online.shopping.constants.ErrorConstants;
import com.online.shopping.entity.Seller;
import com.online.shopping.exception.SellerNotFoundException;
import com.online.shopping.mapper.SellerMapper;
import com.online.shopping.repository.ProductRepository;
import com.online.shopping.repository.RoleRepository;
import com.online.shopping.repository.SellerRepository;
import com.online.shopping.requestdto.SellerRequestDto;
import com.online.shopping.responsedto.SellerResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private SellerMapper sellerMapper;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ProductRepository productRepository;

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
        } else {
            throw new SellerNotFoundException(ErrorConstants.SELLER_EXIST_ERROR);
        }

    }

    public String removeSeller(int sellerId) {
        Seller seller = sellerRepository.findById(sellerId).orElseThrow(() -> new SellerNotFoundException(ErrorConstants.SELLER_NOT_FOUND_ERROR + sellerId));
        sellerRepository.delete(seller);
        return "Successfully deleted the seller where seller id:" + sellerId;
    }

}
