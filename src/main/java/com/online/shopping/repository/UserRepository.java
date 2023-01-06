package com.online.shopping.repository;

import com.online.shopping.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUserName(String userName);

    @Query(nativeQuery = true, value = "select u.id from user as u where u.username = :username")
    int findIdByUserName(@Param("username") String userName);

    boolean existsByUserNameAndPassword(String userName, String password);
}
