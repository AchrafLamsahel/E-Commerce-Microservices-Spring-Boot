package org.cataloguemicroservice.app;

import lombok.AllArgsConstructor;
import org.cataloguemicroservice.dtos.BreadcrumbDTO;
import org.cataloguemicroservice.dtos.CategoryPageDTO;
import org.cataloguemicroservice.dtos.ThreeCategory;
import org.cataloguemicroservice.entities.Category;
import org.cataloguemicroservice.entities.Product;
import org.cataloguemicroservice.repositories.CategoryRepository;
import org.cataloguemicroservice.repositories.ProductRepository;
import org.cataloguemicroservice.services.ICategoryService;
import org.cataloguemicroservice.services.IProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CategoryApp {
    @Qualifier("ICategoryService")
    private final ICategoryService iCategoryService;
    private final IProductService iProductService;
    private final CategoryRepository categoryRepository;


    public ThreeCategory getIndex(Integer pageNumber, Integer pageSize, String sort) {
        ThreeCategory threeCategory = new ThreeCategory();
        threeCategory.setCategoriesTrees(iCategoryService.getHierarchyCategories().getCategoriesTrees());
        threeCategory.setAllProductsPage(iCategoryService.getCategoryPagination(pageNumber, pageSize, sort));
        return threeCategory;
    }

    public CategoryPageDTO getCategorySlug(String slug) {
        CategoryPageDTO categoryPageDTO = new CategoryPageDTO();
        Category category = iCategoryService.getCategoryBySlug(slug);
        if (category.getIdParent() == 0) {
            List<Category> categoryList = categoryRepository.findCategoriesByIdParent(category.getCategoryId());
            List<Product> productList = iProductService.getProductById(category.getCategoryId());
            categoryPageDTO.setRootCategory(category);
            categoryPageDTO.setSubCategories(categoryList);
            categoryPageDTO.setProducts(productList);
            BreadcrumbDTO breadcrumbDTO = new BreadcrumbDTO("/" + category.getSlug(), category.getLabel());
            categoryPageDTO.getBreadcrumbDTO().add(breadcrumbDTO);
            return categoryPageDTO;
        }
        Category rootCategory = iCategoryService.getCategoryById(category.getIdParent());
        categoryPageDTO.setRootCategory(rootCategory);
        List<Product> productList = iProductService.getProductById(category.getCategoryId());
        categoryPageDTO.setProducts(productList);
        BreadcrumbDTO breadcrumbDTO1 = new BreadcrumbDTO("/" + rootCategory.getSlug(), rootCategory.getLabel());
        BreadcrumbDTO breadcrumbDTO = new BreadcrumbDTO("/" + rootCategory.getSlug() + "/" + category.getSlug(),  category.getLabel());
        categoryPageDTO.getBreadcrumbDTO().add(breadcrumbDTO1);
        categoryPageDTO.getBreadcrumbDTO().add(breadcrumbDTO);
        return categoryPageDTO;
    }


}
