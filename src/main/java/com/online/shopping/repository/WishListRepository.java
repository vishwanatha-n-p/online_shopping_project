package com.online.shopping.repository;

import com.online.shopping.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Integer> {

    Optional<WishList> findByUserId(int userId);

    @Query(nativeQuery = true, value = "SELECT product_id FROM wishlist_product WHERE wishlist_id IN (SELECT id FROM wishlist WHERE user_id = :user_id)")
    List<Integer> findAllProductIdByUserId(@Param("user_id") int userId);

}
