package org.example.productcatalogservice.controllers;

import org.example.productcatalogservice.DTOs.ProductDTO;
import org.example.productcatalogservice.models.Product;
import org.example.productcatalogservice.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//import org.springframework.test.context.bean.override.mockito.MockitoBean;


@SpringBootTest
public class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    //@MockitoBean
    private IProductService productService;

    @Test
    public void testGetProductById_withValidId_RunSuccessfully() throws Exception {

        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        product.setName("IPhone16");

        when(productService.GetProductById(productId))
                .thenReturn(product);

        ResponseEntity<ProductDTO> productDTOResponseEntity = productController.getProduct(productId);

        assertNotNull(productDTOResponseEntity);
        assertNotNull(productDTOResponseEntity.getBody());
        assertEquals(productId, productDTOResponseEntity.getBody().getId());
        assertEquals("IPhone16", productDTOResponseEntity.getBody().getName());

        verify(productService,times(1))
                .GetProductById(productId);
    }

    @Test
    public void testGetProductById_withNegativeId_ThrowsException() throws Exception {

        assertThrows(IllegalArgumentException.class, () -> productController.getProduct(-1L));

        verify(productService,times(0)).GetProductById(-1L);
    }

    @Test
    public void testGetProductById_withIdAsZero_ThrowsException() throws Exception {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> productController.getProduct(0L));

        assertEquals("Product id must be greater than 0", exception.getMessage());

        verify(productService,times(0)).GetProductById(0L);
    }
}
