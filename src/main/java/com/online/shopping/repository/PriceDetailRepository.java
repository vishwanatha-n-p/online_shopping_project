package com.online.shopping.repository;

import com.online.shopping.entity.PriceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Optional;

@Repository
public interface PriceDetailRepository extends JpaRepository<PriceDetail, Integer> {

    List<PriceDetail> findAllByProductId(int productId);

    @Query(nativeQuery = true, value = "SELECT * FROM price_detail WHERE product_id= :product_id AND product_count > 0 LIMIT 1")
    Optional<PriceDetail> findFirstByProductId(@Param("product_id") int productId);

    @Query(nativeQuery = true, value = "SELECT * FROM price_detail WHERE price= :price AND discount= :discount AND special_offer_discount= :special_offer_discount AND delivery_charge= :delivery_charge AND final_price= :final_price AND product_id= :product_id AND seller_name= :seller_name AND currency_id= :currency_id")
    Optional<PriceDetail> findPriceDetail(@Param("price") Long price, @Param("discount") String discount, @Param("special_offer_discount") int specialOfferDiscount, @Param("delivery_charge") int deliveryCharge, @Param("final_price") Long finalPrice, @Param("product_id") Integer productId, @Param("seller_name") String sellerName, @Param("currency_id") Integer currencyId);
}
