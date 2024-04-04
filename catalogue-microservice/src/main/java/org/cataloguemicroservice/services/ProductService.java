package org.cataloguemicroservice.services;

import lombok.AllArgsConstructor;
import org.cataloguemicroservice.entities.Product;
import org.cataloguemicroservice.enums.CustomerMessageError;
import org.cataloguemicroservice.exceptions.CategoryNotFoundException;
import org.cataloguemicroservice.exceptions.ProductNotFoundException;
import org.cataloguemicroservice.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService implements IProductService{
    private final ProductRepository productRepository;
    @Override
    public Product save(Product product) {
        product.setSlug(this.slugify(product.getLabel()));
        product.setCreatedDate(new Date());
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductById(Long id) {
        List<Product> products = productRepository.findProductsByIdCategory(id);
        if (products.isEmpty()) {
            throw new ProductNotFoundException(CustomerMessageError.PRODUCT_NOT_FOUND.getMessage());
        }
        return products;
    }

    @Override
    public Product getProductBySlug(String slug) {
        return productRepository.findBySlug(slug)
                .orElseThrow(() -> new
                        CategoryNotFoundException(
                                CustomerMessageError.CATEGORY_NOT_FOUND_SLUG_EQUALS.getMessage()+slug));
    }

    @Override
    public Product getProductByLabel(String label) {
        return productRepository.findByLabel(label)
                .orElseThrow(() -> new
                        CategoryNotFoundException(
                                CustomerMessageError.CATEGORY_NOT_FOUND_WITH_LABEL_EQUALS.getMessage()+label));
    }

    @Override
    public Page<Product> getProductPagination(Integer pageNumber, Integer pageSize, String sort) {
        Pageable pageable = null;
        if (sort != null) {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, sort);
        } else {
            pageable = PageRequest.of(pageNumber, pageSize);
        }
        return productRepository.findAll(pageable);
    }
}
