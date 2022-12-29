package com.online.shopping.repository;

import com.online.shopping.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Integer> {

    List<ProductOrder> findAllByUserId(int userId);

    @Query(nativeQuery = true, value = "SELECT * FROM product_order WHERE product_id= :product_id AND user_id= :user_id")
    Optional<ProductOrder> findByProductIdAndUserId(@Param("product_id")int productId, @Param("user_id") int userId);

    @Query(nativeQuery = true, value = "SELECT sum(cost) FROM product_order WHERE user_id= :user_id")
    long findCostSumByUserId(@Param("user_id") int userId);

}
