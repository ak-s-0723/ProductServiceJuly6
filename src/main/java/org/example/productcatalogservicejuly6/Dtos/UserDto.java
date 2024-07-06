package org.example.productcatalogservicejuly6.Dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.productcatalogservicejuly6.Models.Role;

@Getter
@Setter
public class UserDto {
    private String email;
    private Role role;
    private Long id;
}
