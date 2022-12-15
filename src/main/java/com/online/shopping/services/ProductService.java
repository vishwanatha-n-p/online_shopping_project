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
import com.online.shopping.mapper.HighlightsMapper;
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
import java.util.Optional;
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
    private HighlightsService highlightsService;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private HighlightsMapper highlightsMapper;

    @Autowired
    private PriceDetailRepository priceDetailRepository;

    @Autowired
    private PriceDetailMapper priceDetailMapper;

    public ProductResponseDto addProduct(int sellerId, ProductRequestDto productRequestDto) {
        Product product = sellerRepository.findById(sellerId).map(seller -> {
            int productId = productRequestDto.getId();
            if (validateSellerProduct(seller, productId)) {
                throw new ProductNotFoundException(ErrorConstants.PRODUCT_EXIST_ERROR);
            }
            if (productId != 0) {
                Product productResponse = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(ErrorConstants.PRODUCT_NOT_FOUND_ERROR + productId));
                productResponse.setProductCount(productResponse.getProductCount() + productRequestDto.getProductCount());
                productResponse.setStatus(ProductStatus.AVAILABLE);
                seller.addProductToSeller(productResponse);
                sellerRepository.save(seller);
                return productRepository.save(productResponse);
            }
            Product productResponse = this.addProduct(productRequestDto);
            seller.addProductToSeller(productResponse);
            sellerRepository.save(seller);
            return productResponse;
        }).orElseThrow(() -> new ProductNotFoundException(ErrorConstants.SELLER_NOT_FOUND_ERROR + sellerId));
        return productMapper.convertEntityToDto(product);
    }

    private Boolean validateSellerProduct(Seller seller, int productId) {
        return seller.getProducts().stream().anyMatch(a -> a.getId() == productId);
    }

    public Product addProduct(ProductRequestDto productRequestDto) {
        Highlights highlights = productRequestDto.getHighlights();
        Product productRequest = productMapper.convertDtoToEntity(productRequestDto);
        productRequest.setStatus(ProductStatus.AVAILABLE);
        if (highlights.getId() != 0) {
            Optional<Highlights> highlights1 = highlightsRepository.findById(highlights.getId());
            highlights1.ifPresent(h -> productRequest.setHighlights(h));
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

    public String deactivateProduct(int productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(ErrorConstants.PRODUCT_NOT_FOUND_ERROR + productId));
        List<Seller> sellers = sellerRepository.findSellersByProductsId(productId);
        if (sellers.isEmpty()) {
            product.setStatus(ProductStatus.INACTIVE);
            return "Successfully deactivated the Product form Product list,\nSellers not found for given product id:" + productId;
        }
        product.setStatus(ProductStatus.INACTIVE);
        return sellers.stream().map(s -> deactivateSellersProduct(s.getId(), productId)).collect(Collectors.toSet()).toString();
    }

    public String deactivateSellersProduct(int sellerId, int productId) {
        Seller seller = sellerRepository.findById(sellerId).orElseThrow(() -> new SellerNotFoundException(ErrorConstants.SELLER_NOT_EXIST_ERROR + sellerId));
        seller.getProducts().stream().filter(p -> !ObjectUtils.isEmpty(p) && p.getId() == productId).forEach(a -> a.setStatus(ProductStatus.INACTIVE));
        sellerRepository.save(seller);
        return "Successfully deactivated the Product form Seller product list, Where product id:" + productId;
    }

    public String activateProduct(int productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(ErrorConstants.PRODUCT_NOT_FOUND_ERROR + productId));
        List<Seller> sellers = sellerRepository.findSellersByProductsId(productId);
        if (sellers.isEmpty()) {
            product.setStatus(ProductStatus.AVAILABLE);
            return "Successfully activated the Product form Product list,\nSellers not found for given product id:" + productId;
        }
        product.setStatus(ProductStatus.AVAILABLE);
        return sellers.stream().map(s -> activateSellersProduct(s.getId(), productId)).collect(Collectors.toSet()).toString();
    }

    public String activateSellersProduct(int sellerId, int productId) {
        Seller seller = sellerRepository.findById(sellerId).orElseThrow(() -> new SellerNotFoundException(ErrorConstants.SELLER_NOT_EXIST_ERROR + sellerId));
        seller.getProducts().stream().filter(p -> !ObjectUtils.isEmpty(p) && p.getId() == productId).forEach(a -> a.setStatus(ProductStatus.AVAILABLE));
        sellerRepository.save(seller);
        return "Successfully deactivated the Product form Seller product list, Where product id:" + productId;
    }

    public String deleteProduct(int productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(ErrorConstants.PRODUCT_NOT_FOUND_ERROR + productId));
        List<Seller> sellers = sellerRepository.findSellersByProductsId(productId);
        if (sellers.isEmpty()) {
            productRepository.delete(product);
            return "Successfully deleted the Product form Product list,\nSellers not found for id:" + productId;
        }
        sellers.stream().forEach(s -> deleteSellersProduct(s.getId(), productId));
        productRepository.delete(product);
        return "Successfully deleted the Product form Product list and Seller product list, where product id:" + productId;
    }

    public String deleteSellersProduct(int sellerId, int productId) {
        Seller seller = sellerRepository.findById(sellerId).orElseThrow(() -> new SellerNotFoundException(ErrorConstants.SELLER_NOT_EXIST_ERROR + sellerId));
        seller.getProducts().stream().filter(p -> !ObjectUtils.isEmpty(p) && p.getId() == productId).forEach(a -> seller.removeSellerProduct(a));
        sellerRepository.save(seller);
        return "Successfully deleted the Product form Seller product list, Where product id:" + productId;
    }

}
