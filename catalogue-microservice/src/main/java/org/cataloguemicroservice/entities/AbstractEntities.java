package org.cataloguemicroservice.entities;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.repository.Update;

import java.time.Instant;
import java.time.LocalDate;

@Data
public class AbstractEntities {
    @CreatedDate
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
