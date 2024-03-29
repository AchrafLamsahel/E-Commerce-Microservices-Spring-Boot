package org.cataloguemicroservice.base;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepositories<T, ID> extends MongoRepository<T,ID> {
    Optional<T> findBySlug(String slug);
    Optional<T> findByLabel(String categoryName);
    List<T> findByValidIsTrue();
}
