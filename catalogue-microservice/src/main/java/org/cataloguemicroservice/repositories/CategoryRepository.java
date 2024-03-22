package org.cataloguemicroservice.repositories;

import org.cataloguemicroservice.base.BaseRepositories;
import org.cataloguemicroservice.entities.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository  extends BaseRepositories<Category,Integer> {

}
