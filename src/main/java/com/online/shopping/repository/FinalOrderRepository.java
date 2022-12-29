package com.online.shopping.repository;

import com.online.shopping.entity.FinalOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinalOrderRepository extends JpaRepository<FinalOrder, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM final_order AS f JOIN payment p ON p.id=f.payment_id JOIN customer_detail cd ON cd.id=p.customer_detail_id WHERE cd.user_id= :user_id ")
    List<FinalOrder> findAllByUserId(@Param("user_id") int userId);

    List<FinalOrder> findAllByPaymentId(int i);

}
