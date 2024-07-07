package org.example.productcatalogservicejuly6.Services;

import org.example.productcatalogservicejuly6.Models.Product;

import java.util.List;

public interface IProductService {
    Product getProductById(Long productId);

    Product createProduct(Product product);

    Product getProductBasedOnUserScope(Long userId,Long productId);

    List<Product> getAllProducts();
}
