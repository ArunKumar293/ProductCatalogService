package org.example.productcatalogservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.productcatalogservice.DTOs.ProductDTO;
import org.example.productcatalogservice.models.Product;
import org.example.productcatalogservice.services.IProductService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerMVCTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllProducts_RunSuccessfully() throws Exception {

        Product product = new Product();
        product.setId(1L);
        product.setName("Iphone16");
        List<Product> products = new ArrayList<>();
        products.add(product);

        when(productService.GetAllProducts()).thenReturn(products);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Iphone16");
        List<ProductDTO> productDTOs = new ArrayList<>();
        productDTOs.add(productDTO);

        String response =  objectMapper.writeValueAsString(productDTOs);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().string(response));

    }

    @Test
    public void testGetProductById_RunSuccessfully() throws Exception {

        Product product = new Product();
        product.setId(1L);
        product.setName("Iphone16");

        when(productService.GetProductById(1L)).thenReturn(product);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Iphone16");

        String response =  objectMapper.writeValueAsString(productDTO);

        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(response));

    }

    @Test
    public void testAddProduct_RunSuccessfully() throws Exception {

        Product product = new Product();
        product.setId(1L);
        product.setName("Iphone16");

        when(productService.AddProduct(any(Product.class)))
                .thenReturn(product);

        ProductDTO  productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Iphone16");

        String productDTOString =  objectMapper.writeValueAsString(productDTO);

        mockMvc.perform(post("/products")
                .content(productDTOString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(productDTOString));
    }
}
