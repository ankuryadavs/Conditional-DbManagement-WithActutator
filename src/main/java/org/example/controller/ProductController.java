package org.example.controller;

import org.example.mysql.entity.Book;
import org.example.postgres.entity.Product;
import org.example.postgres.repo.ProductRepo;
import org.example.service.postgres.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/save")
    public ResponseEntity addProduct(@RequestBody Product product)
    {
        Product savedBook=productService.saveProduct(product);
        return ResponseEntity.ok(product);
    }
}
