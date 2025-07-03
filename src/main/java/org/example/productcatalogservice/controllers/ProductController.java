package org.example.productcatalogservice.controllers;

import org.example.productcatalogservice.DTOs.CategoryDTO;
import org.example.productcatalogservice.DTOs.ProductDTO;
import org.example.productcatalogservice.models.Category;
import org.example.productcatalogservice.models.Product;
import org.example.productcatalogservice.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private IProductService productService;

    public ProductDTO ProductToProductDTO(Product product)
    {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setImageURL(product.getImageUrl());

        if(product.getCategory() != null){
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(product.getCategory().getId());
            categoryDTO.setName(product.getCategory().getName());
            categoryDTO.setDescription(product.getCategory().getDescription());
            productDTO.setCategoryDTO(categoryDTO);
        }

        return productDTO;

    }

    @GetMapping("/products")
    public List<ProductDTO> getProducts() {
        List<ProductDTO> products = new ArrayList<>();

        //dummy product
        ProductDTO product = new ProductDTO();
        product.setId(1L);
        product.setName("Product 1");
        product.setDescription("Description 1");

        products.add(product);

        return products;
    }

    @GetMapping("/products/{id}")
    public ProductDTO getProduct(@PathVariable Long id) {

        Product product = productService.GetProductById(id);
        return  ProductToProductDTO(product);
    }
}
