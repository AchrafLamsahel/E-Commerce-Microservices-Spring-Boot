package org.cataloguemicroservice.web;

import lombok.AllArgsConstructor;
import org.cataloguemicroservice.app.CategoryApp;
import org.cataloguemicroservice.app.ProductApp;
import org.cataloguemicroservice.dtos.CategoryPageDTO;
import org.cataloguemicroservice.dtos.ThreeCategory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {
    private final CategoryApp categoryApp;
    private final ProductApp productApp;
    @GetMapping("/{pageNumber}/{pageSize}")
    private ThreeCategory handleCategories(@PathVariable Integer pageNumber, @PathVariable Integer pageSize, String sort) {
        return categoryApp.getIndex(pageNumber,pageSize,sort);
    }

    @GetMapping("/{categorySlug}")
    public CategoryPageDTO productRoutes(@PathVariable("categorySlug") String categorySlug) {
        return categoryApp.getCategorySlug(categorySlug);
    }



}
