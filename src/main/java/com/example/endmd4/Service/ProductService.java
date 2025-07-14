package com.example.endmd4.Service;

import com.example.endmd4.model.Product;
import com.example.endmd4.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Page<Product> search(String name, Double price, Long typeId, Pageable pageable) {
        return productRepository.findByNameContainingAndPriceGreaterThanEqualAndProductType_Id(name, price, typeId, pageable);
    }

    public Page<Product> findAll(Pageable pageable){
        return productRepository.findAll(pageable);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public void deleteById(Product product) {
        productRepository.deleteById(product.getId());
    }
}
