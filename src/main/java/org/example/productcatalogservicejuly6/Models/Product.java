package org.example.productcatalogservicejuly6.Models;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product extends BaseModel {

    //PUBLIC = 0
    //PRIVATE = 1
    @Enumerated(EnumType.ORDINAL)
    private Visibility visibility;

    private String name;

    private String description;

    private String imageUrl;

    private Double price;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;
}
