package org.example.productcatalogservicejuly6.Controllers;

import org.example.productcatalogservicejuly6.Dtos.CategoryDto;
import org.example.productcatalogservicejuly6.Dtos.ProductDto;
import org.example.productcatalogservicejuly6.Models.Category;
import org.example.productcatalogservicejuly6.Models.Product;
import org.example.productcatalogservicejuly6.Services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping
    public List<ProductDto> getAllProducts() {
        List<ProductDto> results = new ArrayList<>();
        List<Product> products = productService.getAllProducts();
        for(Product product : products) {
            results.add(from(product));
        }

        return results;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long productId) {
        try {
            if (productId <= 0) {
                throw new IllegalArgumentException("invalid productId");
            }

            Product product = productService.getProductById(productId);
            ProductDto body = from(product);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("called by", "anurag khanna");
            return new ResponseEntity<>(body, headers, HttpStatus.OK);
        } catch(IllegalArgumentException ex) {
            throw ex;
        }
    }

    @GetMapping("/{userId}/{productId}")
    public ProductDto getProductBasedOnUserScope(@PathVariable("userId") Long userId,@PathVariable("productId") Long productId) {
       try {
           Product product = productService.getProductBasedOnUserScope(userId, productId);
           if(product == null) {
               throw new RuntimeException("Something went wrong");
           }

           return from(product);
       }catch (Exception ex) {
           throw ex;
       }
    }


    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        Product product = from(productDto);
        Product result = productService.createProduct(product);
        return from(result);
    }

    private Product from(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        product.setDescription(productDto.getDescription());
        product.setVisibility(productDto.getVisibility());
        if(productDto.getCategory() != null) {
            Category category = new Category();
            category.setName(productDto.getCategory().getName());
            category.setId(productDto.getCategory().getId());
            category.setDescription(productDto.getCategory().getDescription());
            product.setCategory(category);
        }
        return product;
    }

    private ProductDto from(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setPrice(product.getPrice());
        productDto.setId(product.getId());
        productDto.setVisibility(product.getVisibility());
        if(product.getCategory() != null) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName(product.getCategory().getName());
            categoryDto.setDescription(product.getCategory().getDescription());
            categoryDto.setId(product.getCategory().getId());
            productDto.setCategory(categoryDto);
        }
        return productDto;
    }
}
