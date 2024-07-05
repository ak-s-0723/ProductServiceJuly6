package org.example.productcatalogservicejuly6.Models;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseModel {
    @Id
    private Long id;

    private Date createdAt;

    private Date lastUpdatedAt;

    @Enumerated(EnumType.ORDINAL)
    private State state;
}
