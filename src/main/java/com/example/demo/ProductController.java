package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private List<Product> products = new ArrayList<>();

    public ProductController() {
        products.add(new Product(1, "Laptop", 55000, 10));
        products.add(new Product(2, "Phone", 20000, 25));
        products.add(new Product(3, "Mouse", 500, 100));
    }

    // GET /products  -> all products
    @GetMapping
    public List<Product> getAll() {
        return products;
    }

    // GET /products/2  -> one product
    @GetMapping("/{id}")
    public Product getById(@PathVariable int id) {
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    // POST /products  -> add a product (data comes in the request body as JSON)
    @PostMapping
    public Product add(@RequestBody Product product) {
        products.add(product);
        return product;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        products.removeIf(p -> p.getId() == id);
    }
}