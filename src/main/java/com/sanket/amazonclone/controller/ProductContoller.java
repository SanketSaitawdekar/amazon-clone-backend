package com.sanket.amazonclone.controller;

import com.sanket.amazonclone.model.ProductCategory;
import com.sanket.amazonclone.service.ProductCategoryService;
import com.sanket.amazonclone.utilities.Exception.ResourceNotFoundException;
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

    @PutMapping("/updateCategory/{categoryId}")
    public ResponseEntity<ApiResponse<ProductCategory>> updateProductCategory(
            @PathVariable int categoryId,
            @RequestBody ProductCategory updatedCategory) {
        try {
            ProductCategory updatedCategoryResponse = productCategoryService.updateProductCategory(categoryId, updatedCategory);
            ApiResponse<ProductCategory> response = new ApiResponse<>(HttpStatus.OK.value(), "Category updated successfully", updatedCategoryResponse, null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            ApiResponse<ProductCategory> response = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), e.getMessage(), null, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            ApiResponse<ProductCategory> response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error updating category", null, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/deleteCategory/{categoryId}")
    public ResponseEntity<ApiResponse<ProductCategory>> deleteProductCategory(
            @PathVariable int categoryId) {
        try {
            ProductCategory updatedCategoryResponse = productCategoryService.deleteProductCategory(categoryId);
            ApiResponse<ProductCategory> response = new ApiResponse<>(HttpStatus.OK.value(), "Category deleted successfully", updatedCategoryResponse, null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            ApiResponse<ProductCategory> response = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), e.getMessage(), null, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            ApiResponse<ProductCategory> response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error deleting category", null, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
