package com.online.shopping.repository;

import com.online.shopping.entity.MyOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyOrderRepository extends JpaRepository<MyOrders, Integer> {

    List<MyOrders> findAllByUserId(int userId);

    String findProductNameById(int myOrderId);
}
