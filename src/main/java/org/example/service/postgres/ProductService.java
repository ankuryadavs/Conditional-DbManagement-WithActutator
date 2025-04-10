package org.example.service.postgres;

import lombok.AllArgsConstructor;
import org.example.postgres.entity.Product;
import org.example.postgres.repo.ProductRepo;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepo productRepo;

    public Product saveProduct(Product product)
    {
        return  productRepo.save(product);
    }

}
