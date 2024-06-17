package com.sanket.amazonclone.service;

import com.sanket.amazonclone.model.ProductCategory;
import com.sanket.amazonclone.repository.ProductCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepo productCategoryRepo;

    public ProductCategory addProductCategory(ProductCategory productCategory) {
        return productCategoryRepo.save(productCategory);
    }
}
