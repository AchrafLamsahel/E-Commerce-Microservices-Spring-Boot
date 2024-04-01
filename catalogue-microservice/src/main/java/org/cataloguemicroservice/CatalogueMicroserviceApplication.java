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
import org.cataloguemicroservice.services.IProductService;
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
    private final IProductService iProductService;
    private final ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(CatalogueMicroserviceApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            Product product =Product.builder().productId(1L).valid(true).label("Achraf2040'14893 32jdiwj").productTitle("ProductTitle test for ----->!").idCategory(1L).build();
            Product product1 =Product.builder().productId(2L).valid(true).label("Achraf2040'14893 32jdiwj").productTitle("ProductTitle test for ----->!").idCategory(1L).build();
            Product product2 =Product.builder().productId(3L).valid(true).label("Achraf2040'14893 32jdiwj").productTitle("ProductTitle test for ----->!").idCategory(2L).build();
            Product product3 =Product.builder().productId(4L).valid(true).label("Achraf2040'14893 32jdiwj").productTitle("ProductTitle test for ----->!").idCategory(4L).build();
            Product product4 =Product.builder().productId(5L).valid(true).label("Achraf2040'14893 32jdiwj").productTitle("ProductTitle test for ----->!").idCategory(3L).build();
            Product product5 =Product.builder().productId(6L).valid(true).label("Achraf2040'14893 32jdiwj").productTitle("ProductTitle test for ----->!").idCategory(4L).build();
            Product product6 =Product.builder().productId(7L).valid(true).label("Achraf2040'14893 32jdiwj").productTitle("ProductTitle test for ----->!").idCategory(4L).build();
            Category category = Category.builder().categoryId(1L).idParent(0L).categoryTitle("title Category title !").valid(true).label("Product/&% testCategory").imageUrl("http://localhost:8080/test").build();
            Category category1 = Category.builder().categoryId(2L).idParent(1L).categoryTitle("title Category title !").valid(true).label("Product/&% testCategory").imageUrl("http://localhost:8080/test").build();
            Category category2 = Category.builder().categoryId(3L).idParent(0L).categoryTitle("title Category title !").valid(true).label("Product/&% testCategory").imageUrl("http://localhost:8080/test").build();
            Category category3 = Category.builder().categoryId(5L).idParent(3L).categoryTitle("title Category title !").valid(true).label("Product/&% testCategory").imageUrl("http://localhost:8080/test").build();
            Category category4 = Category.builder().categoryId(4L).idParent(0L).categoryTitle("title Category title !").valid(true).label("Product/&% testCategory").imageUrl("http://localhost:8080/test").build();
            Category category5 = Category.builder().categoryId(6L).idParent(2L).categoryTitle("title Category title !").valid(true).label("Product/&% testCategory").imageUrl("http://localhost:8080/test").build();
            Category category6 = Category.builder().categoryId(7L).idParent(0L).categoryTitle("title Category title !").valid(true).label("Product/&% testCategory").imageUrl("http://localhost:8080/test").build();

            iProductService.save(product);
            iProductService.save(product1);
            iProductService.save(product2);
            iProductService.save(product3);
            iProductService.save(product4);
            iProductService.save(product5);
            iProductService.save(product6);

            iCategoryService.save(category);
            iCategoryService.save(category1);
            iCategoryService.save(category2);
            iCategoryService.save(category3);
            iCategoryService.save(category4);
            iCategoryService.save(category5);
            iCategoryService.save(category6);

            productRepository.findAll().forEach(System.out::println);

        };
    }


}
