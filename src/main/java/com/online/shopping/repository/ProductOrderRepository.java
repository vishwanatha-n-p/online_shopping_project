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

    Optional<ProductOrder> findByProductIdAndUserId(int productId, int userId);

    @Query(nativeQuery = true, value = "select sum(cost) from product_order as po where po.user_id = :user_id")
    long findCostSumByUserId(@Param("user_id") int userId);

}
