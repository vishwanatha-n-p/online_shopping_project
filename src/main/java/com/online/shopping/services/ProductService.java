package com.online.shopping.services;

import com.online.shopping.constants.ErrorConstants;
import com.online.shopping.entity.Highlights;
import com.online.shopping.entity.Product;
import com.online.shopping.entity.ProductType;
import com.online.shopping.entity.Seller;
import com.online.shopping.enums.ProductStatus;
import com.online.shopping.exception.ProductNotFoundException;
import com.online.shopping.exception.ProductTypeNotFoundException;
import com.online.shopping.exception.SellerNotFoundException;
import com.online.shopping.general.Validate;
import com.online.shopping.mapper.PriceDetailMapper;
import com.online.shopping.mapper.ProductMapper;
import com.online.shopping.repository.HighlightsRepository;
import com.online.shopping.repository.PriceDetailRepository;
import com.online.shopping.repository.ProductRepository;
import com.online.shopping.repository.ProductTypeRepository;
import com.online.shopping.repository.SellerRepository;
import com.online.shopping.requestdto.ProductRequestDto;
import com.online.shopping.responsedto.PriceDetailResponseDto;
import com.online.shopping.responsedto.ProductResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private HighlightsRepository highlightsRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private Validate validate;

    @Autowired
    private PriceDetailRepository priceDetailRepository;

    @Autowired
    private PriceDetailMapper priceDetailMapper;

    public ProductResponseDto addProduct(String authorization, ProductRequestDto productRequestDto) {
        Seller sellerResponse = sellerRepository.findByUserId(validate.getUserId(authorization)).orElse(null);
        if (Objects.isNull(sellerResponse)) {
            throw new SellerNotFoundException(ErrorConstants.SELLER_NOT_EXIST_ERROR);
        }
        int sellerId = sellerResponse.getId();
        Product product = sellerRepository.findById(sellerId).map(seller -> {
            Product obtainedProduct = productRepository.findProduct(productRequestDto.getProductName(), productRequestDto.getProductTypeId(), productRequestDto.getColor()).orElse(null);
            if (Objects.nonNull(obtainedProduct)) {
                if (validateSellerProduct(seller, obtainedProduct.getId())) {
                    throw new ProductNotFoundException(ErrorConstants.PRODUCT_EXIST_ERROR);
                }
                obtainedProduct.setProductCount(obtainedProduct.getProductCount() + productRequestDto.getProductCount());
                obtainedProduct.setStatus(ProductStatus.AVAILABLE);
                seller.addProductToSeller(obtainedProduct);
                sellerRepository.save(seller);
                return productRepository.save(obtainedProduct);
            } else {
                Product productResponse = this.saveProduct(productRequestDto);
                seller.addProductToSeller(productResponse);
                sellerRepository.save(seller);
                return productResponse;

            }
        }).orElseThrow(() -> new ProductNotFoundException(ErrorConstants.SELLER_NOT_FOUND_ERROR + sellerId));
        return productMapper.convertEntityToDto(product);
    }

    private Boolean validateSellerProduct(Seller seller, int productId) {
        return seller.getProducts().stream().anyMatch(a -> a.getId() == productId);
    }

    public Product saveProduct(ProductRequestDto productRequestDto) {
        Highlights highlights = productRequestDto.getHighlights();
        Highlights highlightsResponse = highlightsRepository.findHighlights(highlights.getModelNumber(), highlights.getFeatures(), highlights.getSize()).orElse(null);
        Product productRequest = productMapper.convertDtoToEntity(productRequestDto);
        productRequest.setStatus(ProductStatus.AVAILABLE);
        if (Objects.nonNull(highlightsResponse)) {
            productRequest.setHighlights(highlightsResponse);
        } else {
            highlights.setUpdatedAt(LocalDateTime.now());
            productRequest.setHighlights(highlightsRepository.save(highlights));
        }
        ProductType productType = productTypeRepository.findById(productRequestDto.getProductTypeId()).orElseThrow(() -> new ProductTypeNotFoundException(ErrorConstants.PRODUCT_TYPE_NOT_EXIST_ERROR));
        productRequest.setProductType(productType);
        return productRepository.save(productRequest);
    }

    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll().stream().map(product -> productMapper.convertEntityToDto(product)).collect(Collectors.toList());
    }

    public ProductResponseDto getSingleProduct(int productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(ErrorConstants.PRODUCT_NOT_FOUND_ERROR + productId));
        return productMapper.convertEntityToDto(product);
    }

    public List<ProductResponseDto> getParticularProductTypeProducts(int productTypeId) {
        return productRepository.findAllByProductTypeId(productTypeId).stream().map(product -> productMapper.convertEntityToDto(product)).collect(Collectors.toList());
    }

    public List<PriceDetailResponseDto> getProductPriceDetail(int productId) {
        return priceDetailRepository.findAllByProductId(productId).stream().map(pd -> priceDetailMapper.convertEntityToDto(pd)).collect(Collectors.toList());
    }

    public ProductResponseDto deactivateProduct(int productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(ErrorConstants.PRODUCT_NOT_FOUND_ERROR + productId));
        List<Seller> sellers = sellerRepository.findSellersByProductsId(productId);
        if (sellers.isEmpty()) {
            product.setStatus(ProductStatus.INACTIVE);
            productRepository.save(product);
            return productMapper.convertEntityToDto(product);
        }
        product.setStatus(ProductStatus.INACTIVE);
        List<ProductResponseDto> result = sellers.stream().map(s -> deactivateSellersProduct(s.getId(), product)).collect(Collectors.toList());
        productRepository.save(product);
        return result.get(0);
    }

    public ProductResponseDto deactivateSellersProduct(int sellerId, Product product) {
        Seller seller = sellerRepository.findById(sellerId).orElseThrow(() -> new SellerNotFoundException(ErrorConstants.SELLER_NOT_EXIST_ERROR + sellerId));
        seller.getProducts().stream().filter(p -> !ObjectUtils.isEmpty(p) && p.hashCode() == product.hashCode() && p.equals(product)).forEach(a -> a.setStatus(ProductStatus.INACTIVE));
        sellerRepository.save(seller);
        return productMapper.convertEntityToDto(product);
    }

    public ProductResponseDto activateProduct(int productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(ErrorConstants.PRODUCT_NOT_FOUND_ERROR + productId));
        List<Seller> sellers = sellerRepository.findSellersByProductsId(productId);
        if (sellers.isEmpty()) {
            product.setStatus(ProductStatus.AVAILABLE);
            productRepository.save(product);
            return productMapper.convertEntityToDto(product);
        }
        product.setStatus(ProductStatus.AVAILABLE);
        List<ProductResponseDto> result = sellers.stream().map(s -> activateSellersProduct(s.getId(), product)).collect(Collectors.toList());
        productRepository.save(product);
        return result.get(0);
    }

    public ProductResponseDto activateSellersProduct(int sellerId, Product product) {
        Seller seller = sellerRepository.findById(sellerId).orElseThrow(() -> new SellerNotFoundException(ErrorConstants.SELLER_NOT_EXIST_ERROR + sellerId));
        seller.getProducts().stream().filter(p -> !ObjectUtils.isEmpty(p) && p.hashCode() == product.hashCode() && p.equals(product)).forEach(a -> a.setStatus(ProductStatus.AVAILABLE));
        sellerRepository.save(seller);
        return productMapper.convertEntityToDto(product);
    }

    public ProductResponseDto deleteProduct(int productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(ErrorConstants.PRODUCT_NOT_FOUND_ERROR + productId));
        List<Seller> sellers = sellerRepository.findSellersByProductsId(productId);
        if (sellers.isEmpty()) {
            productRepository.delete(product);
            return productMapper.convertEntityToDto(product);
        }
        sellers.stream().forEach(s -> deleteSellersProduct(s.getId(), productId));
        productRepository.delete(product);
        return productMapper.convertEntityToDto(product);
    }

    public ProductResponseDto deleteSellersProduct(int sellerId, int productId) {
        Seller seller = sellerRepository.findById(sellerId).orElseThrow(() -> new SellerNotFoundException(ErrorConstants.SELLER_NOT_EXIST_ERROR + sellerId));
        List<Product> filteredProduct = seller.getProducts().stream().filter(p -> !ObjectUtils.isEmpty(p) && p.getId() == productId).collect(Collectors.toList());
        if (!filteredProduct.isEmpty()) {
            seller.removeSellerProduct(filteredProduct);
            sellerRepository.save(seller);
            return productMapper.convertEntityToDto(filteredProduct.get(0));
        }
        throw new ProductNotFoundException(ErrorConstants.PRODUCT_NOT_FOUND_IN_SELLER_ERROR);
    }

}
