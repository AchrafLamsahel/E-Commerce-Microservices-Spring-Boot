package org.cataloguemicroservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product extends AbstractEntities {
    private Integer productId;
    private String label;
    private String slug;
    private String productTitle;
    private String imageUrl;
    private Category category;
}
