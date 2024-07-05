package org.example.productcatalogservicejuly6.Models;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Category extends BaseModel {
    private String name;

    private String description;

    @OneToMany(mappedBy = "category")
    private List<Product> productList = new ArrayList<>();
}
