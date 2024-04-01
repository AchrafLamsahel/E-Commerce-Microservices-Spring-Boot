package org.cataloguemicroservice.app;

import lombok.AllArgsConstructor;
import org.cataloguemicroservice.entities.Product;
import org.cataloguemicroservice.repositories.ProductRepository;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@AllArgsConstructor
public class ProductApp {
    private final ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }



}
