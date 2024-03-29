package org.cataloguemicroservice.web;

import lombok.AllArgsConstructor;
import org.cataloguemicroservice.dtos.CategoryPageDTO;
import org.cataloguemicroservice.dtos.ProductDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.function.ServerRequest;

@Controller
@AllArgsConstructor
public class CategoryController {
    @GetMapping("/products")
    private CategoryPageDTO handleCategories(ServerRequest request) {
        return null;
    }

    @GetMapping("/products/{categorySlug}")
    public CategoryPageDTO productRoutes() {
        return null;
    }

    public ProductDTO handlegetProducts() {
        return null;
    }
}
