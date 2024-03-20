package org.cataloguemicroservice.base;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepositories<T, ID> {
    T findBySlug(String slug);
    T findByLabel(String categoryName);
}
