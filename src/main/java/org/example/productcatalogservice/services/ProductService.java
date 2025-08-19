package org.example.productcatalogservice.services;

import org.example.productcatalogservice.Repository.ProductRepository;
import org.example.productcatalogservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class ProductService implements IProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product GetProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    @Override
    public List<Product> GetAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product AddProduct(Product product) {

        Optional<Product> optionalProduct = productRepository.findById(product.getId());

        if (optionalProduct.isEmpty()) {
            return productRepository.save(product);
        }
        return optionalProduct.get();
    }

    @Override
    public Product UpdateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void DeleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}
