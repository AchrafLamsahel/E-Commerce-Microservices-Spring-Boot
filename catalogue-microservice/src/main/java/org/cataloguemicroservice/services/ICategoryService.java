package org.cataloguemicroservice.services;

import org.cataloguemicroservice.base.BaseSlugService;
import org.cataloguemicroservice.dtos.ThreeCategory;
import org.cataloguemicroservice.entities.Category;
import org.cataloguemicroservice.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICategoryService extends BaseSlugService<Category> {
    List<Category> getAllCategories();
    Category getCategoryById(Long id);
    Category getCategoryByIdParent(Long idParent);
    Category getCategoryBySlug(String slug);
    Category getCategoryByLabel(String label);
    List<Category> getParentCategory();
    ThreeCategory getHierarchyCategories();
    Page<Product> getCategoryPagination(Integer pageNumber, Integer pageSize, String sort);
}
