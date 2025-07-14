package com.example.endmd4.repository;

import com.example.endmd4.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByNameContainingAndPriceGreaterThanEqualAndProductType_Id(String name, double price, Long typeId, Pageable pageable);
}
