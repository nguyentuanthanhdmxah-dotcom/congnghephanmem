package com.example.saleappv1.service;

import com.example.saleappv1.model.Product;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private List<Product> products = new ArrayList<>();

    public ProductService() {
        loadProducts();
    }

    private void loadProducts() {

        try {

            ObjectMapper objectMapper = new ObjectMapper();

            InputStream inputStream =
                    new ClassPathResource("data/products.json")
                            .getInputStream();

            products = objectMapper.readValue(
                    inputStream,
                    new TypeReference<List<Product>>() {}
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductById(Integer id) {

        for (Product product : products) {

            if (product.getId().equals(id)) {
                return product;
            }
        }

        return null;
    }
    
    public List<Product> filterProducts(
            Integer categoryId,
            String keyword,
            Double fromPrice,
            Double toPrice) {

        List<Product> result = new ArrayList<>();

        for (Product product : products) {

            boolean match = true;

            if (categoryId != null
                    && !product.getCategoryId().equals(categoryId)) {

                match = false;
            }

            if (keyword != null
                    && !keyword.trim().isEmpty()) {

                if (!product.getName()
                        .toLowerCase()
                        .contains(keyword.toLowerCase())) {

                    match = false;
                }
            }

            if (fromPrice != null
                    && product.getPrice() < fromPrice) {

                match = false;
            }

            if (toPrice != null
                    && product.getPrice() > toPrice) {

                match = false;
            }

            if (match) {
                result.add(product);
            }
        }

        return result;
    }
}