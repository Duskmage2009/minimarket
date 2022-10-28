package com.example.minimarket.services;


import com.example.minimarket.models.Image;
import com.example.minimarket.models.Product;
import com.example.minimarket.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    public void saveProduct(Product product, MultipartFile file1,MultipartFile file2,MultipartFile file3) throws IOException {
        Image image1;
        Image image2;
        Image image3;
        if(file1.getSize() !=0){
            image1 =toImageEntity(file1);
            image1.setPreviewImage(true);
            product.addImageToProduct(image1);
        }
        if(file2.getSize() !=0){
            image2 =toImageEntity(file2);
            product.addImageToProduct(image2);
        }
        if(file3.getSize() !=0){
            image3 =toImageEntity(file3);
            product.addImageToProduct(image3);
        }
        log.info("Saving new Product. Title: {}; Author: {}", product.getTitle(),product.getAuthor());
        Product productFromDb = productRepository.save(product);
        productFromDb.setPreviewImageId(productFromDb.getImages().get(0).getId());
        productRepository.save(product);
        // product.setId(++ID);
        // products.add(product);
    }

    private Image toImageEntity(MultipartFile file) throws IOException{
        Image image =new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }


    public void deleteProduct(Long id) {
        // products.removeIf(product -> product.getId().equals(id));
        productRepository.deleteById(id);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);

    }
}

