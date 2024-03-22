package org.cataloguemicroservice.repositories;

import org.cataloguemicroservice.base.BaseRepositories;
import org.cataloguemicroservice.entities.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository  extends BaseRepositories<Product,Integer> {

}
