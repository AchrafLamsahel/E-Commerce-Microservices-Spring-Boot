package org.cataloguemicroservice.base;

import reactor.core.publisher.Mono;

public interface BaseSlugService<T,DTO> {
    DTO save(T p);

    default String createSlug(String label) {
        return label.trim()
                .toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-")
                .replaceAll("-{2,}", "-");
    }
}
