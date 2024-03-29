package org.cataloguemicroservice.dtos;

import lombok.Data;
import org.cataloguemicroservice.entities.Category;

import java.util.ArrayList;
import java.util.List;
@Data
public class CategoryWithChildren {
    private Category category;
    private List<CategoryWithChildren> children;

    public CategoryWithChildren(Category category) {
        this.category = category;
        this.children = new ArrayList<>();
    }
}
