package org.cataloguemicroservice.repositories;

import org.cataloguemicroservice.base.BaseRepositories;
import org.cataloguemicroservice.entities.Category;
import org.cataloguemicroservice.entities.Product;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository  extends BaseRepositories<Category,Long> {
    List<Category> findCategoriesByIdParent(Long id);
    List<Category> findByIdParent(Long id);
}
