package org.example.productcatalogservicejuly6.Services;

import org.example.productcatalogservicejuly6.Models.Product;
import org.example.productcatalogservicejuly6.Repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StorageProductService  implements IProductService {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public Product getProductById(Long productId) {
      Optional<Product> productOptional = productRepo.findProductByIdEquals(productId);
      if(productOptional.isPresent()) {
          return productOptional.get();
      }

      return null;
    }

    public Product createProduct(Product product) {
        return productRepo.save(product);
    }
}
