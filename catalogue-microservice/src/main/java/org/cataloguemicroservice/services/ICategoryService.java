package org.cataloguemicroservice.services;

import org.cataloguemicroservice.base.BaseSlugService;
import org.cataloguemicroservice.dtos.CategoryDTO;
import org.cataloguemicroservice.entities.Category;
import org.springframework.stereotype.Service;

@Service
public interface ICategoryService extends BaseSlugService<Category, CategoryDTO> {

}
