package com.online.shopping.repository;

import com.online.shopping.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    Optional<Address> findByStreetAndCityAndState(String street, String city, String state);

}
