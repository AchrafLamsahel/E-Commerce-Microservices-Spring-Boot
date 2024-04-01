package org.cataloguemicroservice.web;

import lombok.AllArgsConstructor;
import org.cataloguemicroservice.app.CategoryApp;
import org.cataloguemicroservice.app.ProductApp;
import org.cataloguemicroservice.dtos.CategoryPageDTO;
import org.cataloguemicroservice.dtos.ProductDTO;
import org.cataloguemicroservice.dtos.dto.ThreeCategory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.ServerRequest;

@RestController
@AllArgsConstructor
public class CategoryController {
    private final CategoryApp categoryApp;
    private final ProductApp productApp;
    @GetMapping("/products")
    private ThreeCategory handleCategories() {
        return categoryApp.getIndex();
    }

    @GetMapping("/products/{categorySlug}")
    public CategoryPageDTO productRoutes() {
        return null;
    }

    public ProductDTO handlegetProducts() {
        return null;
    }
}
