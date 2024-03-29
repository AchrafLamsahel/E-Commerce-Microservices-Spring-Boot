package org.cataloguemicroservice.services;

import org.cataloguemicroservice.base.BaseSlugService;
import org.cataloguemicroservice.dtos.ProductDTO;
import org.cataloguemicroservice.entities.Category;
import org.cataloguemicroservice.entities.Product;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IProductService extends BaseSlugService<Product> {
    List<Product> getAllProducts();
    List<Product> getProductById(Long id);
    Product getProductBySlug(String slug);
    Product getProductByLabel(String label);
}
