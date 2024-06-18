package com.sanket.amazonclone.service;

import com.sanket.amazonclone.model.ProductCategory;
import com.sanket.amazonclone.repository.ProductCategoryRepo;
import com.sanket.amazonclone.utilities.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepo productCategoryRepo;

    public ProductCategory addProductCategory(ProductCategory productCategory) {
        return productCategoryRepo.save(productCategory);
    }
    public ProductCategory updateProductCategory(int categoryId, ProductCategory updatedCategory) {
        Optional<ProductCategory> existingCategoryOptional = productCategoryRepo.findById(categoryId);

        if (existingCategoryOptional.isPresent()) {
            ProductCategory existingCategory = existingCategoryOptional.get();
            existingCategory.setCategory_name(updatedCategory.getCategory_name());
            existingCategory.setIs_deleted(updatedCategory.getIs_deleted());
            return productCategoryRepo.save(existingCategory);
        } else {
            throw new ResourceNotFoundException("Product category not found with id " + categoryId);
        }
    }

    public ProductCategory deleteProductCategory(Integer categoryId) {
        Optional<ProductCategory> existingCategoryOptional = productCategoryRepo.findById(categoryId);

        if (existingCategoryOptional.isPresent()) {
            ProductCategory existingCategory = existingCategoryOptional.get();
            existingCategory.setIs_deleted("Y");
            return productCategoryRepo.save(existingCategory);
        } else {
            throw new ResourceNotFoundException("Product category not found with id " + categoryId);
        }
    }
}
