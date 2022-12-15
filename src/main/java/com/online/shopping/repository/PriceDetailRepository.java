package com.online.shopping.repository;

import com.online.shopping.entity.PriceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PriceDetailRepository extends JpaRepository<PriceDetail, Integer> {

    List<PriceDetail> findAllByProductId(int productId);

    @Query(nativeQuery = true, value = "select * from price_detail as pd where pd.product_id= :product_id AND pd.product_count > 0 limit 1")
    Optional<PriceDetail> findFirstByProductId(@Param("product_id") int productId);

}
