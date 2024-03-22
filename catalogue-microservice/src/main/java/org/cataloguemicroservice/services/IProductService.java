package org.cataloguemicroservice.services;

import org.cataloguemicroservice.base.BaseSlugService;
import org.cataloguemicroservice.dtos.ProductDTO;
import org.cataloguemicroservice.entities.Product;
import org.springframework.stereotype.Service;

@Service
public interface IProductService extends BaseSlugService<Product, ProductDTO> {

}
