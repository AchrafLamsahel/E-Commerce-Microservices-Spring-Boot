package org.cataloguemicroservice.services;

import org.cataloguemicroservice.base.BaseSlugService;
import org.cataloguemicroservice.entities.Product;
import org.springframework.data.domain.Page;
import java.util.List;


public interface IProductService extends BaseSlugService<Product> {
    List<Product> getAllProducts();
    List<Product> getProductById(Long id);
    Product getProductBySlug(String slug);
    Product getProductByLabel(String label);
    Page<Product> getProductPagination(Integer pageNumber, Integer pageSize, String sort);

}
