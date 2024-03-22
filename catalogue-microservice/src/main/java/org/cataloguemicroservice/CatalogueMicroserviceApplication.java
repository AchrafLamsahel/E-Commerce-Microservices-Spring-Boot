package org.cataloguemicroservice;

import lombok.AllArgsConstructor;
import org.cataloguemicroservice.dtos.CategoryDTO;
import org.cataloguemicroservice.entities.Category;
import org.cataloguemicroservice.entities.Product;
import org.cataloguemicroservice.repositories.CategoryRepository;
import org.cataloguemicroservice.repositories.ProductRepository;
import org.cataloguemicroservice.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Random;

@SpringBootApplication
@AllArgsConstructor
public class CatalogueMicroserviceApplication {
    private final ICategoryService iCategoryService;
    public static void main(String[] args) {
        SpringApplication.run(CatalogueMicroserviceApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(CategoryRepository categoryRepository, ProductRepository productRepository){
        return args -> {
            /*
            Category electronics = new Category(1,0,"Electronics", "electronics", "Electronics", "ddwe", new HashSet<>(), null, new HashSet<>());
            Category clothing = new Category(2,0, "Clothing", "clothing", "Clothing", null, new HashSet<>(), null, new HashSet<>());
            Product laptop = new Product(3, "Laptop", "laptop", "Laptop", null, electronics);
            Product shirt = new Product(4, "Shirt", "shirt", "Shirt", null, clothing);
            electronics.getProducts().add(laptop);
            clothing.getProducts().add(shirt);
            categoryRepository.save(electronics);
            categoryRepository.save(clothing);
            productRepository.save(laptop);
            productRepository.save(shirt);
             */
            Category electronics = new Category(3,0,"Electronics", "sadasd", "Electronics", "ddwe");
            CategoryDTO categoryDTO=iCategoryService.save(electronics);
            System.out.println(categoryDTO.getCategoryId());
        };
    }

}
