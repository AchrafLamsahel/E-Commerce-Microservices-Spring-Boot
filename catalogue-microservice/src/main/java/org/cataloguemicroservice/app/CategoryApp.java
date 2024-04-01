package org.cataloguemicroservice.app;

import lombok.AllArgsConstructor;
import org.cataloguemicroservice.dtos.dto.CategoriesTree;
import org.cataloguemicroservice.dtos.CategoryPageDTO;
import org.cataloguemicroservice.dtos.dto.ThreeCategory;
import org.cataloguemicroservice.entities.Category;
import org.cataloguemicroservice.entities.Product;
import org.cataloguemicroservice.enums.CustomerMessageError;
import org.cataloguemicroservice.exceptions.CategoryNotFoundException;
import org.cataloguemicroservice.repositories.CategoryRepository;
import org.cataloguemicroservice.repositories.ProductRepository;
import org.cataloguemicroservice.services.ICategoryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class CategoryApp {
    @Qualifier("ICategoryService")
    private final ICategoryService iCategoryService;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;


   public ThreeCategory getIndex(){
       ThreeCategory threeCategory = new ThreeCategory();
       threeCategory.setCategoriesTrees(iCategoryService.getHierarchyCategories().getCategoriesTrees());
       List<Product> productList = productRepository.findAll();
       threeCategory.setAllProducts(productList);
       return threeCategory;
   }

    public CategoryPageDTO getWithProducts(String slug) {
        CategoryPageDTO categoryPageDTO = new CategoryPageDTO();
        Category category = categoryRepository.findBySlug(slug)
                .orElseThrow(() -> new
                        CategoryNotFoundException(CustomerMessageError.CATEGORY_NOT_FOUND.getMessage()));
        categoryPageDTO.setCategory(category);
        List<Product> productList = productRepository.findProductsByIdCategory(category.getIdParent());
        categoryPageDTO.setProducts(productList);
        Optional<Category> parentCategory = categoryRepository.findById(category.getIdParent());
        if (productList.isEmpty()) {
            categoryPageDTO.setCategory(category);
        } else {
            categoryPageDTO.setCategory(parentCategory.get());
        }
        return categoryPageDTO;
    }

    public ThreeCategory getCategoryWithProductsRecursive() {
        ThreeCategory threeList = new ThreeCategory();
        List<Category> rootCategoryList = categoryRepository.findByIdParent(0L);
        for (Category category : rootCategoryList) {
            CategoriesTree categoriesThree = new CategoriesTree();
            categoriesThree.setRootCategory(category);
            List<Category> subCategories = categoryRepository.findByIdParent(category.getCategoryId());
            if (subCategories != null && !subCategories.isEmpty()) {
                categoriesThree.setChildren(subCategories);
            }
            List<CategoriesTree> categoriesTrees =new ArrayList<>();
            categoriesTrees.add(categoriesThree);
           // threeList.addCategoriesTree(categoriesThree);
        }
        return threeList;
    }





}
