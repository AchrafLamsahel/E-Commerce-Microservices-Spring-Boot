package org.cataloguemicroservice.app;

import lombok.AllArgsConstructor;
import org.cataloguemicroservice.dtos.BreadcrumbDTO;
import org.cataloguemicroservice.dtos.ProductDetailsDTO;
import org.cataloguemicroservice.entities.Category;
import org.cataloguemicroservice.entities.Product;
import org.cataloguemicroservice.repositories.ProductRepository;
import org.cataloguemicroservice.services.ICategoryService;
import org.cataloguemicroservice.services.IProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ProductApp {
    private final ProductRepository productRepository;
    private final IProductService iProductService;
    @Qualifier("ICategoryService")
    private final ICategoryService iCategoryService;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public ProductDetailsDTO getProductBySlug(String slug) {
        ProductDetailsDTO productDetailsDTO = new ProductDetailsDTO();
        Product product = iProductService.getProductBySlug(slug);
        Category category = iCategoryService.getCategoryByIdParent(product.getIdCategory());
        if (category.getIdParent() == 0) {
            productDetailsDTO.setRootCategory(category);
            productDetailsDTO.setProduct(product);
            BreadcrumbDTO breadcrumbDTO = new BreadcrumbDTO("/" + category.getSlug() + "/" + product.getSlug(),
                    category.getLabel() + product.getSlug());
            productDetailsDTO.getBreadcrumbDTO().add(breadcrumbDTO);
            return productDetailsDTO;
        }
        Category rootCategory = iCategoryService.getCategoryById(category.getIdParent());
        productDetailsDTO.setRootCategory(rootCategory);
        productDetailsDTO.setSubCategory(category);
        productDetailsDTO.setProduct(product);
        BreadcrumbDTO breadcrumbDTO = new BreadcrumbDTO("/" + rootCategory.getSlug(), rootCategory.getLabel());
        BreadcrumbDTO breadcrumbDTO1 = new BreadcrumbDTO("/" + rootCategory.getSlug() + "/" + category.getSlug(), category.getSlug());
        BreadcrumbDTO breadcrumbDTO2 = new BreadcrumbDTO("/" + rootCategory.getSlug() + "/" + category.getSlug() + "/" + product.getSlug(), product.getSlug());
        productDetailsDTO.getBreadcrumbDTO().add(breadcrumbDTO);
        productDetailsDTO.getBreadcrumbDTO().add(breadcrumbDTO1);
        productDetailsDTO.getBreadcrumbDTO().add(breadcrumbDTO2);
        return productDetailsDTO;
    }

}
