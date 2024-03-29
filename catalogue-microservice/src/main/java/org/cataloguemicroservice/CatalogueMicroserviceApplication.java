package org.cataloguemicroservice;

import lombok.AllArgsConstructor;
import org.cataloguemicroservice.app.CategoryApp;
import org.cataloguemicroservice.dtos.CategoryDTO;
import org.cataloguemicroservice.dtos.CategoryPageDTO;
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
    //private final ICategoryService iCategoryService;
    private final CategoryApp categoryApp;

    public static void main(String[] args) {
        SpringApplication.run(CatalogueMicroserviceApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            /*
            Category electronics = new Category(119L,0L,"Eléctronics lîmsèahel", "", "Electronics", "ddwe");
            Category clothing = new Category(201L,0L, "Clothiéng Achréaf", "", "Clothing","jhfkwe");
            Product laptop = new Product(302L, "Lap top", "lçptop", "Laptop", "fsdf",1L);
            Product shirt = new Product(453L, "Shi rt", "shirt", "Shirt", "fsdf",1L);
            iCategoryService.save(electronics);
            iCategoryService.save(clothing);

            productRepository.save(laptop);
            productRepository.save(shirt);
            */
            //CategoryPageDTO c = categoryApp.getWithProducts("clothieng-achreaf");
            categoryApp.getCategoryWithProductsRecursive();

        };
    }


}
