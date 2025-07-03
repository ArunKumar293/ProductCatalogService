package org.example.productcatalogservice.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {

    private Long id;

    private String name;

    private String description;

    private Double price;

    private  String imageURL;

    private  CategoryDTO categoryDTO;
}
