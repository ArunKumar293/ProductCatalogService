package org.example.productcatalogservice.services;

import org.example.productcatalogservice.client.FakeStoreAPIClient;
import org.example.productcatalogservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeStoreProductService implements  IProductService{

    @Autowired
    private FakeStoreAPIClient fakeStoreAPIClient;

    @Override
    public Product GetProductById(Long id) {
        return fakeStoreAPIClient.GetProductById(id);
    }

    @Override
    public List<Product> GetAllProducts() {
        return fakeStoreAPIClient.GetAllProducts();
    }

    @Override
    public Product AddProduct(Product product) {
        return fakeStoreAPIClient.AddProduct(product);
    }

    @Override
    public Product UpdateProduct(Product product) {
        return fakeStoreAPIClient.UpdateProduct(product);
    }

    @Override
    public void DeleteProduct(Long productId) {
        fakeStoreAPIClient.DeleteProduct(productId);
    }

}
