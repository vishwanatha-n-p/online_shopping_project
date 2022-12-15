package com.online.shopping.repository;

import com.online.shopping.entity.ProductSubcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSubcategoryRepository extends JpaRepository<ProductSubcategory, Integer> {

    List<ProductSubcategory> findAllByProductCategoryId(int categoryId);

}
