package org.cataloguemicroservice.mappers;

import org.cataloguemicroservice.base.BaseProductMapper;
import org.cataloguemicroservice.dtos.ProductDTO;
import org.cataloguemicroservice.entities.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper  {
    Product productDtoToProduct(ProductDTO productDTO);
}
