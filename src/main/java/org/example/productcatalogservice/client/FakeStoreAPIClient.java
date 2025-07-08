package org.example.productcatalogservice.client;

import org.example.productcatalogservice.DTOs.FakeStoreProductDTO;
import org.example.productcatalogservice.DTOs.ProductDTO;
import org.example.productcatalogservice.models.Category;
import org.example.productcatalogservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class FakeStoreAPIClient {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public Product FakeStoreProductDTOToProduct(FakeStoreProductDTO fakeStoreProductDTO) {
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

    public FakeStoreProductDTO ProductToFakeStoreProductDTO(Product product) {
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setId(product.getId());
        fakeStoreProductDTO.setTitle(product.getName());
        fakeStoreProductDTO.setDescription(product.getDescription());
        fakeStoreProductDTO.setPrice(product.getPrice());
        fakeStoreProductDTO.setImage(product.getImageUrl());
        if (product.getCategory() != null) {
            fakeStoreProductDTO.setCategory(product.getCategory().getName());
        }
        return fakeStoreProductDTO;
    }

    public Product GetProductById(Long productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity = restTemplate.getForEntity(
                "https://fakestoreapi.com/products/{id}", FakeStoreProductDTO.class, productId
        );
        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductDTOResponseEntity.getBody();
        if (fakeStoreProductDTO != null && fakeStoreProductDTOResponseEntity.getStatusCode() == HttpStatus.valueOf(200)) {
            return FakeStoreProductDTOToProduct(fakeStoreProductDTO);
        }
        return null;
    }

    public List<Product> GetAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<List<FakeStoreProductDTO>> fakeStoreProductDTOResponseEntity = restTemplate.exchange(
                "https://fakestoreapi.com/products",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<FakeStoreProductDTO>>() {
                }
        );
        List<FakeStoreProductDTO> fakeStoreProductDTOs = fakeStoreProductDTOResponseEntity.getBody();

        if (fakeStoreProductDTOs != null && !fakeStoreProductDTOs.isEmpty()
                && fakeStoreProductDTOResponseEntity.getStatusCode() == HttpStatus.valueOf(200)) {

            List<Product> products = new ArrayList<Product>();
            for (FakeStoreProductDTO fakeStoreProductDTO : fakeStoreProductDTOs) {
                products.add(FakeStoreProductDTOToProduct(fakeStoreProductDTO));
            }
            return products;
        }

        return Collections.emptyList();
    }

    public Product AddProduct(Product product) {
        FakeStoreProductDTO fakeStoreProductDTO = ProductToFakeStoreProductDTO(product);
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity = restTemplate.postForEntity(
                "https://fakestoreapi.com/products", fakeStoreProductDTO,FakeStoreProductDTO.class
        );
        FakeStoreProductDTO addedFakeStoreProductDTO = fakeStoreProductDTOResponseEntity.getBody();
        if(addedFakeStoreProductDTO != null &&
                fakeStoreProductDTOResponseEntity.getStatusCode() == HttpStatus.valueOf(200)) {

            return FakeStoreProductDTOToProduct(addedFakeStoreProductDTO);
        }

        return null;
    }

    public Product UpdateProduct(Product product){
        FakeStoreProductDTO fakeStoreProductDTO  = ProductToFakeStoreProductDTO(product);
        RestTemplate restTemplate = restTemplateBuilder.build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<FakeStoreProductDTO> requestEntity = new HttpEntity<>(fakeStoreProductDTO, headers);

        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity = restTemplate.exchange(
                "https://fakestoreapi.com/products"+product.getId(),
                HttpMethod.PUT,
                requestEntity,
                FakeStoreProductDTO.class
        );
        FakeStoreProductDTO updatedFakeStoreProductDTO = fakeStoreProductDTOResponseEntity.getBody();
        if( updatedFakeStoreProductDTO != null &&
                fakeStoreProductDTOResponseEntity.getStatusCode() == HttpStatus.valueOf(200)) {

            return FakeStoreProductDTOToProduct(updatedFakeStoreProductDTO);
        }

        return null;
    }

    public void DeleteProduct(Long productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.delete("https://fakestoreapi.com/products/{id}", productId);
    }

}
