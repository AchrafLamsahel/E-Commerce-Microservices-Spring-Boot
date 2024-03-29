package org.cataloguemicroservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cataloguemicroservice.entities.Category;
import org.cataloguemicroservice.entities.Product;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDetailsDTO {
    private Product product;
    private Category category;
    private List<Category> subCategories;
    private Category parentCategory;
    private Category rootCategory;
    private List<BreadcrumbDTO> breadcrumbDTO;
}
