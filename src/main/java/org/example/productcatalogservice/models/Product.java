package org.example.productcatalogservice.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Locale;

@Setter
@Getter
public class Product extends BaseModel {

    private  String name;

    private  String description;

    private  double price;

    private String imageUrl;

    private Category category;

    private boolean isPrimeSaleSpecific;
}
