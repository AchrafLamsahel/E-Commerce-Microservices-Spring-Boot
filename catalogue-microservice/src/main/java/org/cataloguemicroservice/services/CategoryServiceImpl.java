package org.cataloguemicroservice.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cataloguemicroservice.dtos.CategoryDTO;
import org.cataloguemicroservice.entities.Category;
import org.cataloguemicroservice.mappers.CategoryMapper;
import org.cataloguemicroservice.repositories.CategoryRepository;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class CategoryServiceImpl implements ICategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDTO save(Category p) {
        p.setSlug(this.slugify(p.getLabel()));
        return categoryMapper.toDto(categoryRepository.save(p));
    }
}
