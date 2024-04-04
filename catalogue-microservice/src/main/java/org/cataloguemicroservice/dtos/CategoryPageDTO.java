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
public class CategoryPageDTO {
    private Category rootCategory;
    private Category subCategory;
    private List<Category> subCategories;
    private List<Product> products;
    private List<BreadcrumbDTO> breadcrumbDTO;
}
