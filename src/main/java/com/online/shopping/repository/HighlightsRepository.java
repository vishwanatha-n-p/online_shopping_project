package com.online.shopping.repository;

import com.online.shopping.entity.Highlights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HighlightsRepository extends JpaRepository<Highlights, Integer> {

    @Query(nativeQuery = true, value = "SELECT model_number FROM highlights h WHERE h.id = :id")
    String findModelNumberById(@Param("id") int highlightsId);

}
