package org.cataloguemicroservice.base;

import java.text.Normalizer;

public interface BaseSlugService<T> {
    static final int MAX_SLUG_LENGTH = 50;

    T save(T p);

    default String slugify(final String s) {
        final String intermediateResult = Normalizer
                .normalize(s, Normalizer.Form.NFD)
                .toLowerCase().trim()
                // Remplacer les caractères non alphanumériques par un tiret
                .replaceAll("[^\\w\\s-]", "")
                // Remplacer les espaces, tirets et underscores par un tiret
                .replaceAll("[\\s_-]+", "-")
                // Supprimer les tirets en début ou fin de chaîne
                .replaceAll("^-+|-+$", "");
        return intermediateResult.substring(0,
                Math.min(MAX_SLUG_LENGTH, intermediateResult.length()));
    }
}
