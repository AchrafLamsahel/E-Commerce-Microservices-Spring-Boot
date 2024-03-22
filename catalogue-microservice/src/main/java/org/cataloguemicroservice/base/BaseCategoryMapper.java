package org.cataloguemicroservice.base;

import org.cataloguemicroservice.dtos.CategoryDTO;
import java.util.List;

public interface BaseCategoryMapper<T> {
    List<T> fromDTOList(List<CategoryDTO> list);

    List<CategoryDTO> toDTOList(List<T> list);
}
