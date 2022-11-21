package com.product.productservice.controller;

import com.product.productservice.model.entity.Product;
import com.product.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public ResponseEntity<List<Product>> getProduct() {
        return ResponseEntity.ok().body(productService.readProduct());
    }

    @GetMapping("/product-by-id")
    public ResponseEntity<Product> getProductById(@RequestParam("id") int id) {
        return ResponseEntity.ok().body(productService.getProductById(id));
    }

    @PostMapping("/product")
    public ResponseEntity<String> saveProduct(@RequestBody Product product) {
        var process = productService.createProduct(product);
        if(process) {
            return ResponseEntity.ok().body("Product successfully saved!");
        }

        return ResponseEntity.badRequest().body("Product failed to save!");
    }

    @PutMapping("/update-product")
    public ResponseEntity<String> updateProduct(@RequestParam int id, @RequestBody Product product) {
        if(productService.updateProduct(id, product)) {
            return ResponseEntity.ok().body("Product has been updated!");
        }

        return ResponseEntity.badRequest().body("Product not found or error!");
    }

    @DeleteMapping("/delete-product")
    public ResponseEntity<String> deleteProduct(@RequestParam int id) {
        if(productService.deleteProduct(id)) {
            return ResponseEntity.ok().body("Product successfully deleted!");
        }

        return ResponseEntity.badRequest().body("Product not found or error!");
    }


}
