package org.cataloguemicroservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.Set;
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category extends AbstractEntities {
    private Integer categoryId;
    private String label;
    private String slug;
    private String categoryTitle;
    private String imageUrl;
    private Set<Category> subCategories;
    private Category parentCategory;
    private Set<Product> products;

}
