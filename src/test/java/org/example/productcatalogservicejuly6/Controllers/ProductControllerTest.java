package org.example.productcatalogservicejuly6.Controllers;

import org.example.productcatalogservicejuly6.Dtos.ProductDto;
import org.example.productcatalogservicejuly6.Models.Product;
import org.example.productcatalogservicejuly6.Services.IProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    private IProductService productService;

    @Captor
    private ArgumentCaptor<Long> idCaptor;

    //Test_when_then

    @DisplayName("getting product with id 1 will work")
    @Test
    void Test_GetProductById_ReturnsProductSuccessfully() {
        //Arrange
        Long id = 1L;
        Product product = new Product();
        product.setId(id);
        product.setName("Iphone");
        when(productService.getProductById(any(Long.class))).thenReturn(product);


        //Act
        ResponseEntity<ProductDto> response = productController.getProductById(id);

        //Assert
        assertNotNull(response.getBody());
        assertEquals(id,response.getBody().getId());
        assertEquals("Iphone",response.getBody().getName());
        verify(productService,
                times(1)).getProductById(1L);
    }

    @DisplayName("getting product with id 0 will throw illegalargumentexception")
    @Test
    void Test_GetProductById_WithInvalidId_ResultsInException() {
        //Act and Assert
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> productController.getProductById(0L));

        assertEquals("please pass productId > 1",ex.getMessage());

        verify(productService,
                times(0)).getProductById(0L);
    }

    @Test
    void Test_ProductServiceInvokedWithCorrectParameters_RunsSuccessfully() {
        //Arrange
        Long id = 1L;
        Product product = new Product();
        product.setId(id);
        product.setName("Iphone");
        when(productService.getProductById(any(Long.class))).thenReturn(product);

        //Act
        productController.getProductById(id);

        //Assert
        verify(productService).getProductById(idCaptor.capture());
        assertEquals(id,idCaptor.getValue());
    }
}
