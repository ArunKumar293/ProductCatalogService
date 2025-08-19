package org.example.productcatalogservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Locale;

@Setter
@Getter
@Entity
public class Product extends BaseModel {

    private  String name;

    private  String description;

    private  double price;

    private String imageUrl;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

    private boolean isPrimeSaleSpecific;
}
