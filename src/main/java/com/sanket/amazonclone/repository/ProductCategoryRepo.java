package com.sanket.amazonclone.repository;

import com.sanket.amazonclone.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepo extends JpaRepository<ProductCategory, Integer> {
}
