package org.cataloguemicroservice;

import lombok.AllArgsConstructor;
import org.cataloguemicroservice.dtos.CategoryDTO;
import org.cataloguemicroservice.entities.Category;
import org.cataloguemicroservice.entities.Product;
import org.cataloguemicroservice.repositories.CategoryRepository;
import org.cataloguemicroservice.repositories.ProductRepository;
import org.cataloguemicroservice.services.ICategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.List;
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

            Category electronics = new Category(119,0,"Eléctronics lîmsèahel", "", "Electronics", "ddwe");
            Category clothing = new Category(201,0, "Clothiéng Achréaf", "", "Clothing","jhfkwe");
            Product laptop = new Product(302, "Lap top", "lçptop", "Laptop", "fsdf",List.of(1,2,3));
            Product shirt = new Product(453, "Shi rt", "shirt", "Shirt", "fsdf",List.of(1,2,3));
            iCategoryService.save(electronics);
            iCategoryService.save(clothing);

            productRepository.save(laptop);
            productRepository.save(shirt);

        };
    }

}
