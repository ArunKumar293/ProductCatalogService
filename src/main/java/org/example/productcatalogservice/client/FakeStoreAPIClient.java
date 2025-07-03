package org.example.productcatalogservice.client;

import org.example.productcatalogservice.DTOs.FakeStoreProductDTO;
import org.example.productcatalogservice.models.Category;
import org.example.productcatalogservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FakeStoreAPIClient {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public Product FakeStoreProductDTOToProduct(FakeStoreProductDTO fakeStoreProductDTO)
    {
        Product product = new Product();
        product.setId(fakeStoreProductDTO.getId());
        product.setName(fakeStoreProductDTO.getTitle());
        product.setDescription(fakeStoreProductDTO.getDescription());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setImageUrl(fakeStoreProductDTO.getImage());

        Category category = new Category();
        category.setName(fakeStoreProductDTO.getCategory());
        product.setCategory(category);

        return product;
    }

    public Product GetProductById(Long productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity = restTemplate.getForEntity(
                "https://fakestoreapi.com/products/{id}", FakeStoreProductDTO.class, productId
        );
        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductDTOResponseEntity.getBody();
        if(fakeStoreProductDTO != null && fakeStoreProductDTOResponseEntity.getStatusCode() == HttpStatus.valueOf(200))
        {
            return FakeStoreProductDTOToProduct(fakeStoreProductDTO);
        }
        return null;

    }
}
