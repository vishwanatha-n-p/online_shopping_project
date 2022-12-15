package com.online.shopping.repository;

import com.online.shopping.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer> {

    List<Seller> findSellersByProductsId(int productId);

}
