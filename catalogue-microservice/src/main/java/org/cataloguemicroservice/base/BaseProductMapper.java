package org.cataloguemicroservice.base;

import org.cataloguemicroservice.dtos.ProductDTO;
import java.util.List;

public interface BaseProductMapper<T> {
    List<T> fromDTOList(List<ProductDTO> list);
    List<ProductDTO> toDTOList(List<T> list);
}
