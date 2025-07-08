package org.example.productcatalogservice.services;

import org.example.productcatalogservice.models.Product;

import java.util.List;

public interface IProductService {

    Product GetProductById(Long id);
    List<Product> GetAllProducts();
    Product AddProduct(Product product);
    Product UpdateProduct(Product product);
    void DeleteProduct(Long productId);
}
