package org.cataloguemicroservice.entities;

import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Data
public class AbstractEntities {
    @CreatedDate
    private DateTime createdOn;

    @LastModifiedDate
    private DateTime updatedOn;
}
