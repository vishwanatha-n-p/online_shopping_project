package com.online.shopping.repository;

import com.online.shopping.entity.CustomerDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerDetailRepository extends JpaRepository<CustomerDetail, Integer> {

    Optional<CustomerDetail> findByUserId(int userId);

    @Query(nativeQuery = true, name = "select * from customer_detail as cd where cd.user_id= :user_id order by cd.user_id,cd.id desc limit 1")
    Optional<CustomerDetail> findLastByUserId(@Param("user_id") int userId);

    @Query(nativeQuery = true, value = "SELECT * FROM customer_detail cd WHERE cd.user_id = :user_id ORDER BY cd.user_id,cd.id DESC LIMIT 1")
    CustomerDetail findLastCustomerDetailByUserId(@Param("user_id") int userId);

}
