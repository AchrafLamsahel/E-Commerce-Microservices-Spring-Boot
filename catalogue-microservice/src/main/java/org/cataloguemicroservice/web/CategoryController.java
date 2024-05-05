package org.cataloguemicroservice.web;

import lombok.AllArgsConstructor;
import org.cataloguemicroservice.app.CategoryApp;
import org.cataloguemicroservice.dtos.CategoryPageDTO;
import org.cataloguemicroservice.dtos.ThreeCategory;
import org.cataloguemicroservice.entities.Category;
import org.cataloguemicroservice.services.ICategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {
    private static final Logger log = LoggerFactory.getLogger(CategoryController.class);
    private final CategoryApp categoryApp;
    @Qualifier("ICategoryService")
    private final ICategoryService iCategoryService;

    @GetMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    private List<Category> getAllCategories() {
        log.info("allProducts");
        return iCategoryService.getAllCategories();
    }


    @GetMapping(value = "/{pageNumber}/{pageSize}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    private ThreeCategory handleCategories(@PathVariable Integer pageNumber, @PathVariable Integer pageSize, String sort) {
        return categoryApp.getIndex(pageNumber, pageSize, sort);
    }

    @GetMapping(value = "/{categorySlug}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public CategoryPageDTO categoryRoutes(@PathVariable("categorySlug") String categorySlug) {
        return categoryApp.getCategorySlug(categorySlug);
    }

    @PostMapping(value = "/admin", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public CategoryPageDTO adminRoutes(@RequestBody CategoryPageDTO categoryPageDTO) {
        return null;
    }

    /**
     * String urlParams = request.pathVariable("categorySlug");
     * String[] segments = urlParams.split("/");
     * String categorySlugParam = segments[segments.length - 1];
     */


}
