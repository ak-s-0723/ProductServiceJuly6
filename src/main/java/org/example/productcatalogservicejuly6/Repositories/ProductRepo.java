package org.example.productcatalogservicejuly6.Repositories;

import org.example.productcatalogservicejuly6.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
    Optional<Product> findProductByIdEquals(Long id);

    Product save(Product product);
}
