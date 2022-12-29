package com.online.shopping.repository;

import com.online.shopping.entity.CartList;
import com.online.shopping.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartListRepository extends JpaRepository<CartList, Integer> {

    Optional<CartList> findByUserId(int userId);

    @Query(nativeQuery = true, value = "SELECT product_id FROM cart_list_product WHERE cart_list_id IN (SELECT id FROM cart_list WHERE user_id= :user_id)")
    List<Integer> findAllProductIdByUserId(@Param("user_id") int userId);

}
