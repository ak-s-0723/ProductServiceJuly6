package org.example.productcatalogservicejuly6.Services;

import org.example.productcatalogservicejuly6.Dtos.UserDto;
import org.example.productcatalogservicejuly6.Models.Product;
import org.example.productcatalogservicejuly6.Models.Role;
import org.example.productcatalogservicejuly6.Models.Visibility;
import org.example.productcatalogservicejuly6.Repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class StorageProductService  implements IProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Product getProductById(Long productId) {
      Optional<Product> productOptional = productRepo.findProductByIdEquals(productId);
      if(productOptional.isPresent()) {
          return productOptional.get();
      }

      return null;
    }

    public Product getProductBasedOnUserScope(Long userId,Long productId) {
         Optional<Product> optionalProduct = productRepo.findProductByIdEquals(productId);
         if(optionalProduct.isEmpty()) {
             return null;
         }

        System.out.println(optionalProduct.get().getVisibility());
         if(optionalProduct.get().getVisibility().equals(Visibility.PUBLIC)) {
             return optionalProduct.get();
         }

         //Otherwise Call UserService
        UserDto userDto = restTemplate.getForEntity("http://userservice/users/{id}", UserDto.class,userId).getBody();
        if(userDto == null) {
            System.out.println("USERDTO IS NULL");
            return null;
        }

        if(userDto.getRole().equals(Role.ADMIN)) {
            System.out.println("userEmail "+userDto.getEmail());
            return optionalProduct.get();
        }
        return null;
     }

    public Product createProduct(Product product) {
        return productRepo.save(product);
    }
}
