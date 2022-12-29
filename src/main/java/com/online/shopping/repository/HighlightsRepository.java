package com.online.shopping.repository;

import com.online.shopping.entity.Highlights;
import com.online.shopping.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HighlightsRepository extends JpaRepository<Highlights, Integer> {

    @Query(nativeQuery = true, value = "SELECT model_number FROM highlights h WHERE h.id = :id")
    String findModelNumberById(@Param("id") int highlightsId);

    @Query(nativeQuery = true, value = "SELECT * FROM highlights WHERE model_number= :model_number AND features= :features AND size= :size")
    Optional<Highlights> findHighlights(@Param("model_number") String modelNumber, @Param("features") String features, @Param("size") String size);

}
