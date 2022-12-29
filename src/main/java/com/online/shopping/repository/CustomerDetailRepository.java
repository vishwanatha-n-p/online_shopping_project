package com.online.shopping.repository;

import com.online.shopping.entity.CustomerDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerDetailRepository extends JpaRepository<CustomerDetail, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM customer_detail WHERE user_id = :user_id ORDER BY user_id, id DESC LIMIT 1")
    Optional<CustomerDetail> findLastCustomerDetailByUserId(@Param("user_id") int userId);

    @Query(nativeQuery = true, value = "SELECT * FROM customer_detail WHERE user_id= :user_id AND first_name= :first_name AND last_name= :last_name AND email= :email")
    Optional<CustomerDetail> findCustomerDetail(@Param("user_id") int userId, @Param("first_name") String firstName, @Param("last_name") String lastName, @Param("email") String email);

}
