package org.cataloguemicroservice.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cataloguemicroservice.dtos.CategoryDTO;
import org.cataloguemicroservice.dtos.dto.CategoriesTree;
import org.cataloguemicroservice.dtos.dto.ThreeCategory;
import org.cataloguemicroservice.entities.Category;
import org.cataloguemicroservice.enums.CustomerMessageError;
import org.cataloguemicroservice.exceptions.CategoryNotFoundException;
import org.cataloguemicroservice.mappers.CategoryMapper;
import org.cataloguemicroservice.repositories.CategoryRepository;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public Category save(Category category) {
        category.setSlug(this.slugify(category.getLabel()));
        category.setCreatedDate(new Date());
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new
                        CategoryNotFoundException(
                                CustomerMessageError.CATEGORY_NOT_FOUND_WITH_ID_EQUALS.getMessage()+ id));
    }

    @Override
    public Category getCategoryByIdParent(Long idParent) {
        return categoryRepository.findById(idParent)
                .orElseThrow(() -> new
                        CategoryNotFoundException(
                                CustomerMessageError.CATEGORY_NOT_FOUND_WITH_ID_PARENT_EQUALS.getMessage()
                                        +idParent));
    }

    @Override
    public Category getCategoryBySlug(String slug) {
        return categoryRepository.findBySlug(slug)
                .orElseThrow(() -> new
                        CategoryNotFoundException(
                                CustomerMessageError.CATEGORY_NOT_FOUND_SLUG_EQUALS.getMessage()+slug));
    }

    @Override
    public Category getCategoryByLabel(String label) {
        return categoryRepository.findByLabel(label)
                .orElseThrow(() -> new
                        CategoryNotFoundException(
                                CustomerMessageError.CATEGORY_NOT_FOUND_WITH_LABEL_EQUALS.getMessage()+label));
    }

    @Override
    public List<Category> getParentCategory() {
        return categoryRepository.findCategoriesByIdParent(0L);
    }

    public ThreeCategory getHierarchyCategories(){
        ThreeCategory threeCategory = new ThreeCategory();
        List<Category> rootCategoryList = categoryRepository.findCategoriesByIdParent(0L);
        for (Category cat : rootCategoryList) {
            CategoriesTree categoriesTree = new CategoriesTree();
            categoriesTree.setRootCategory(cat);
            List<Category> subCategories = categoryRepository.findByIdParent(cat.getCategoryId());
            categoriesTree.setChildren(subCategories);
            threeCategory.getCategoriesTrees().add(categoriesTree);
        }
        return threeCategory;
    }

}
