package com.sanket.amazonclone.controller;

import com.sanket.amazonclone.model.ProductCategory;
import com.sanket.amazonclone.service.ProductCategoryService;
import com.sanket.amazonclone.utilities.Response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductContoller {

    @Autowired
    private ProductCategoryService productCategoryService;

    @PostMapping("/addCategory")
    public ResponseEntity<ApiResponse<ProductCategory>> addProductCategory(@RequestBody ProductCategory productCategory) {
        try {
            System.out.println("category name:"+productCategory.getCategory_name());
            ProductCategory savedCategory = productCategoryService.addProductCategory(productCategory);
            ApiResponse<ProductCategory> response = new ApiResponse<>(HttpStatus.OK.value(), "Category added successfully", savedCategory, null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse<ProductCategory> response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error adding category", null, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
