package com.online.shopping.repository;

import com.online.shopping.entity.PaymentMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentModeRepository extends JpaRepository<PaymentMode, Integer> {

    Optional<PaymentMode> findByModeName(String modeName);

}
