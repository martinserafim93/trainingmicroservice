package com.product.productservice.service;

import com.product.productservice.model.entity.Product;
import com.product.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public boolean createProduct(Product product) {
        if(product != null) {
            productRepository.save(product);
            return true;
        }

        return false;
    }

    public List<Product> readProduct() {
        return productRepository.findAll();
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).get();
    }

    public boolean updateProduct(int id, Product product) {
        var data = productRepository.findById(id);
        if(data.isPresent()) {
            data.get().setProductName(product.getProductName());
            data.get().setProductType(product.getProductType());
            data.get().setProductCode(product.getProductCode());
            data.get().setProductPrice(product.getProductPrice());

            productRepository.save(data.get());
            return true;
        }

        return false;
    }

    public boolean deleteProduct(int id) {
        var data = productRepository.findById(id);
        if(data.isPresent()) {
            productRepository.delete(data.get());
            return true;
        }

        return false;
    }
}
