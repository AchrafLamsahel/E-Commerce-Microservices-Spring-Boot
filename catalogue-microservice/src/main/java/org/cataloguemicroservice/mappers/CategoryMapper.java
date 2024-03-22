package org.cataloguemicroservice.mappers;

import org.cataloguemicroservice.base.BaseCategoryMapper;
import org.cataloguemicroservice.dtos.CategoryDTO;
import org.cataloguemicroservice.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper extends BaseCategoryMapper<Category> {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(source = "categoryId", target = "categoryId")
    @Mapping(source = "idParent", target = "idParent")
    @Mapping(source = "label", target = "label")
    @Mapping(source = "slug", target = "slug")
    @Mapping(source = "categoryTitle", target = "categoryTitle")
    @Mapping(source = "imageUrl", target = "imageUrl")
    CategoryDTO categoryToCategoryDTO(Category category);

    @Mapping(source = "categoryId", target = "categoryId")
    @Mapping(source = "idParent", target = "idParent")
    @Mapping(source = "label", target = "label")
    @Mapping(source = "slug", target = "slug")
    @Mapping(source = "categoryTitle", target = "categoryTitle")
    @Mapping(source = "imageUrl", target = "imageUrl")
    Category categoryDTOToCategory(CategoryDTO categoryDTO);


}
