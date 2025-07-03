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
        return List.of();
    }

    @Override
    public void AddProduct(Product product) {

    }

    @Override
    public void UpdateProduct(Product product) {

    }

    @Override
    public void DeleteProduct(Product product) {

    }

}
