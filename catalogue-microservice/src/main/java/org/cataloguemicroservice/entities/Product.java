package org.cataloguemicroservice.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Document(collation = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product extends AbstractEntities {
    @Id
    private Integer productId;
    private String label;
    @Indexed(unique = true)
    private String slug;
    private String productTitle;
    private String imageUrl;
    private List<Integer> idCategories;
}
