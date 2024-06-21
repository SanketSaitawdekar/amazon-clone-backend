package com.sanket.amazonclone.service;

import com.sanket.amazonclone.model.Product;
import com.sanket.amazonclone.model.ProductCategory;
import com.sanket.amazonclone.repository.ProductRepo;
import com.sanket.amazonclone.utilities.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;
    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    public Product updateProduct(int productId, Product updatedProduct) {
        Optional<Product> existingProductOptional = productRepo.findById(productId);

        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();
            existingProduct.setProduct_name(updatedProduct.getProduct_name());
            existingProduct.setProduct_category_id(updatedProduct.getProduct_category_id());
            existingProduct.setProduct_price(updatedProduct.getProduct_price());
            return productRepo.save(existingProduct);
        } else {
            throw new ResourceNotFoundException("Product not found with id " + productId);
        }
    }

    public Product deleteProduct(Integer productId) {
        Optional<Product> existingProductOptional = productRepo.findById(productId);

        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();
            existingProduct.setIs_deleted("Y");
            return productRepo.save(existingProduct);
        } else {
            throw new ResourceNotFoundException("Product not found with id " + productId);
        }
    }
}
