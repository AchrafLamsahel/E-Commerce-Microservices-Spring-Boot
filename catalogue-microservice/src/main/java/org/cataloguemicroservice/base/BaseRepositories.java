package org.cataloguemicroservice.base;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;
@NoRepositoryBean
public interface BaseRepositories<T, ID> extends MongoRepository<T,ID> {
    T findBySlug(String slug);
    T findByLabel(String categoryName);

}
