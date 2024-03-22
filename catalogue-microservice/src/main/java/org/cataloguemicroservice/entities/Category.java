package org.cataloguemicroservice.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;
@Document(collection = "categories")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category extends AbstractEntities {
    @Id
    private Integer categoryId;
    private Integer idParent;
    private String label;
    @Indexed(unique = true)
    private String slug;
    private String categoryTitle;
    private String imageUrl;

}
