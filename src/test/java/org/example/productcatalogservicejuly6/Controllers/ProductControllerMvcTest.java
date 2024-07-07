package org.example.productcatalogservicejuly6.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.productcatalogservicejuly6.Dtos.ProductDto;
import org.example.productcatalogservicejuly6.Models.Product;
import org.example.productcatalogservicejuly6.Services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void Test_GetAllProducts_RunsSuccessfully() throws Exception {
        //Arrange
        Product product = new Product();
        product.setId(1L);
        product.setName("Iphone15");

        Product product2 = new Product();
        product2.setName("Macbook");
        product2.setId(2L);
        product2.setDescription("Strongest Mac");

        List<Product> productList = new ArrayList<>();
        productList.add(product);
        productList.add(product2);

        when(productService.getAllProducts()).thenReturn(productList);


        //object <-> json <-> string
        //Act and Assert
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(objectMapper.writeValueAsString(productList)))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Iphone15"))
                .andExpect(jsonPath("$[1].length()").value(3));
    }

//    [ {
//        //prioduct1
//    },{
//        //product2
//    }]

    @Test
    public void Test_CreateProduct_RunsSuccessfully() throws Exception {
        //Arrange
        Product product = new Product();
        product.setId(3L);
        product.setName("Anurag");

        ProductDto productDto = new ProductDto();
        productDto.setId(3L);
        productDto.setName("Anurag");

        when(productService.createProduct(any(Product.class)))
                .thenReturn(product);

        //Act and Assert
        mockMvc.perform(post("/products")
                .content(objectMapper.writeValueAsString(productDto)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(objectMapper.writeValueAsString(productDto)))
                .andExpect(jsonPath("$.name").value("Anurag"))
                .andExpect(jsonPath("$.length()").value(2));
    }
}
