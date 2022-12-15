package com.online.shopping.repository;

import com.online.shopping.entity.Product;
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

    @Query(nativeQuery = true, value = "select wp.product_id from wishlist_product as wp where wp.wishlist_id in (select wl.id from wishlist as wl where wl.user_id = :user_id)")
    List<Integer> findAllProductIdByUserId(@Param("user_id") int userId);

    @Query(nativeQuery = true, value = "select * from wishlist_product as wp where wp.wishlist_id in (select wl.id from wishlist as wl where wl.user_id = :user_id)")
    List<Product> findProductByWishListId(@Param("user_id") int userId);

}
