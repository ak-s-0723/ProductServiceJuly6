package org.example.productcatalogservicejuly6.Dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.example.productcatalogservicejuly6.Models.Visibility;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {
    private Long id;

    private String name;

    private String description;

    private String imageUrl;

    private Double price;

    private CategoryDto category;

    private Visibility visibility;
}
