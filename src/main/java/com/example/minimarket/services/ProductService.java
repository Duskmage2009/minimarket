package com.example.minimarket.services;


import com.example.minimarket.models.Product;
import com.example.minimarket.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor

public class ProductService {

    private final ProductRepository productRepository;
    // private List<Product> products = new ArrayList<>();
    private long ID = 0;

    /*{
        products.add(new Product(++ID, "PlayStation 5 ", "Simple description", 20000, "Kiev", "Tommy"));
        products.add(new Product(++ID, "Apple phone 11 ", "Simple description", 33000, "Odessa", "Jonny"));

    }*/

    public List<Product> listProducts(String title) {
        if (title != null) return productRepository.findByTitle(title);
        return productRepository.findAll();
    }

    public void saveProduct(Product product) {

        log.info("Saving new {}", product);
        productRepository.save(product);
        // product.setId(++ID);
        // products.add(product);
    }


    public void deleteProduct(Long id) {
        // products.removeIf(product -> product.getId().equals(id));
        productRepository.deleteById(id);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);

    }
}

