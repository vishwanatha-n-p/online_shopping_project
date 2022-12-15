package com.online.shopping.services;

import com.online.shopping.constants.ErrorConstants;
import com.online.shopping.entity.PriceDetail;
import com.online.shopping.entity.Product;
import com.online.shopping.exception.CurrencyNotFoundException;
import com.online.shopping.exception.PriceDetailNotFoundException;
import com.online.shopping.exception.ProductNotFoundException;
import com.online.shopping.mapper.PriceDetailMapper;
import com.online.shopping.repository.CurrencyRepository;
import com.online.shopping.repository.PriceDetailRepository;
import com.online.shopping.repository.ProductRepository;
import com.online.shopping.requestdto.PriceDetailRequestDto;
import com.online.shopping.responsedto.PriceDetailResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PriceDetailService {

    @Autowired
    private PriceDetailRepository priceDetailRepository;

    @Autowired
    private PriceDetailMapper priceDetailMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    public List<PriceDetailResponseDto> getAllPriceDetail() {
        return priceDetailRepository.findAll().stream().map(currency -> priceDetailMapper.convertEntityToDto(currency)).collect(Collectors.toList());
    }

    public PriceDetailResponseDto getSinglePriceDetail(int priceDetailId) {
        PriceDetail priceDetailType = priceDetailRepository.findById(priceDetailId).orElseThrow(() -> new PriceDetailNotFoundException(ErrorConstants.PRICE_DETAILS_NOT_FOUND_ERROR + priceDetailId));
        return priceDetailMapper.convertEntityToDto(priceDetailType);
    }

    public PriceDetailResponseDto addPriceDetail(PriceDetailRequestDto priceDetailRequestDto) {
        Optional<Product> product = productRepository.findById(priceDetailRequestDto.getProductId());
        if (product.isPresent()) {
            Optional<PriceDetail> priceDetail = priceDetailRepository.findById(priceDetailRequestDto.getId());
            if (!priceDetail.isPresent()) {
                PriceDetail priceDetailRequest = priceDetailMapper.convertDtoToEntity(priceDetailRequestDto);
                priceDetailRequest.setCurrency(currencyRepository.findById(priceDetailRequestDto.getCurrencyId()).orElseThrow(() -> new CurrencyNotFoundException(ErrorConstants.CURRENCY_NOT_EXIST_ERROR)));
                priceDetailRequest.setProduct(product.get());
                return priceDetailMapper.convertEntityToDto(priceDetailRepository.save(priceDetailRequest));
            } else {
                return priceDetailMapper.convertEntityToDto(priceDetailRepository.save(priceDetail.get()));
            }
        } else {
            throw new ProductNotFoundException(ErrorConstants.PRODUCT_NOT_EXIST_ERROR);
        }
    }

    public String removePriceDetail(int priceDetailId) {
        priceDetailRepository.delete(priceDetailRepository.findById(priceDetailId).orElseThrow(() -> new PriceDetailNotFoundException(ErrorConstants.PRICE_DETAILS_NOT_FOUND_ERROR + priceDetailId)));
        return "Successfully deleted the Price details where id:" + priceDetailId;
    }

    public List<PriceDetailResponseDto> getProductPriceDetail(int productId) {
        return priceDetailRepository.findAllByProductId(productId).stream().map(priceDetail -> priceDetailMapper.convertEntityToDto(priceDetail)).collect(Collectors.toList());
    }

}
