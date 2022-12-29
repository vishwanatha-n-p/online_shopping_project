package com.online.shopping.repository;

import com.online.shopping.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAllByProductTypeId(int productTypeId);

    Product findIdByProductName(String productName);

    @Query(nativeQuery = true, value = "SELECT * FROM product WHERE product_name= :product_name AND product_type_id= :product_type_id AND color= :color")
    Optional<Product> findProduct(@Param("product_name") String productName, @Param("product_type_id") int productTypeId, @Param("color") String color);

}
