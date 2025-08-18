package org.example.productcatalogservice.controllers;

import org.example.productcatalogservice.DTOs.CategoryDTO;
import org.example.productcatalogservice.DTOs.ProductDTO;
import org.example.productcatalogservice.models.Category;
import org.example.productcatalogservice.models.Product;
import org.example.productcatalogservice.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    public Product ProductDTOtoProduct(ProductDTO productDTO){
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImageUrl(productDTO.getImageURL());
        if(productDTO.getCategoryDTO() != null){
            Category category = new Category();
            category.setId(productDTO.getCategoryDTO().getId());
            category.setName(productDTO.getCategoryDTO().getName());
            category.setDescription(productDTO.getCategoryDTO().getDescription());
            product.setCategory(category);
        }

        return product;
    }

    @GetMapping("/products")
    public List<ProductDTO> getProducts() {

        List<Product> products = productService.GetAllProducts();

        List<ProductDTO> productDTOs = new ArrayList<>();
        for (Product product : products) {
            productDTOs.add(ProductToProductDTO(product));
        }
        return productDTOs;
    }

    @GetMapping("/products/{id}")
    public ProductDTO getProduct(@PathVariable Long id) {

        if(id <= 0)
        {
            throw new IllegalArgumentException("Product id must be greater than 0");
        }
        Product product = productService.GetProductById(id);
        return  ProductToProductDTO(product);
    }

    @PostMapping("/products")
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO) {
        Product product = ProductDTOtoProduct(productDTO);
        Product addedProduct = productService.AddProduct(product);
        if(addedProduct != null){
            return ProductToProductDTO(addedProduct);
        }
        return null;
    }

    @PutMapping("/products/{id}")
    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        Product product = ProductDTOtoProduct(productDTO);
        Product updatedProduct = productService.UpdateProduct(product);
        if(updatedProduct != null){
            return ProductToProductDTO(updatedProduct);
        }
        return null;
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.DeleteProduct(id);
    }

}
