package org.cataloguemicroservice.web;

import lombok.AllArgsConstructor;
import org.cataloguemicroservice.app.CategoryApp;
import org.cataloguemicroservice.app.ProductApp;
import org.cataloguemicroservice.dtos.CategoryPageDTO;
import org.cataloguemicroservice.dtos.ThreeCategory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {
    private final CategoryApp categoryApp;

    /*
            + Add Category + Update Category
            + Activate and Inactivate Category
    */

    @GetMapping("/{pageNumber}/{pageSize}")
    private ThreeCategory handleCategories(@PathVariable Integer pageNumber, @PathVariable Integer pageSize, String sort) {
        return categoryApp.getIndex(pageNumber, pageSize, sort);
    }

    @GetMapping("/{categorySlug}")
    public CategoryPageDTO categoryRoutes(@PathVariable("categorySlug") String categorySlug) {
        return categoryApp.getCategorySlug(categorySlug);
    }

    @PostMapping("/admin")
    public CategoryPageDTO adminRoutes(@RequestBody CategoryPageDTO categoryPageDTO) {
        return null;
    }

    /**
     * String urlParams = request.pathVariable("categorySlug");
     * String[] segments = urlParams.split("/");
     * String categorySlugParam = segments[segments.length - 1];
     */


}
