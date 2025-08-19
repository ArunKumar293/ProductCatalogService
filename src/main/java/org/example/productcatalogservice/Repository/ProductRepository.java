package org.example.productcatalogservice.Repository;


import org.example.productcatalogservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

     Product save (Product product);

     Optional<Product> findById(Long id);

     List<Product> findAll();

     void deleteById(Long id);

     List<Product> findAllByOrderByPrice();

     @Query("SELECT p.name FROM Product p where p.id = :id")
     String getProductName(Long id);

}
