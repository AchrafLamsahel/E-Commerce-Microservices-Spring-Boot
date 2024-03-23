package org.cataloguemicroservice.entities;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.time.Instant;
import java.time.LocalDate;

@Data
public class AbstractEntities {
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
