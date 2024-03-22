package org.cataloguemicroservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Integer productId;
    private String label;
    private String slug;
    private String productTitle;
    private String imageUrl;
    private List<Integer> idCategories;
}
