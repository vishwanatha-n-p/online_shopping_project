package com.online.shopping.repository;

import com.online.shopping.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer> {

    List<Seller> findSellersByProductsId(int productId);

    Optional<Seller> findByUserId(int userId);

}
