package com.online.shopping.repository;

import com.online.shopping.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Integer> {

    List<ProductType> findAllByProductSubcategoryId(int subcategoryId);

    Optional<ProductType> findByProductType(String productType);
}
