package org.example.productcatalogservicejuly6.Services;

import org.example.productcatalogservicejuly6.Models.Product;

public interface IProductService {
    Product getProductById(Long productId);

    Product createProduct(Product product);
}
