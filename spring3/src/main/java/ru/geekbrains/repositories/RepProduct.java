package ru.geekbrains.repositories;

import org.springframework.stereotype.Repository;
import ru.geekbrains.model.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class RepProduct {

    private List<Product> products;

    @PostConstruct
    public void init() {
        this.products = new ArrayList<>();
        this.products.add(new Product(1, "Product1", 10.0));
        this.products.add(new Product(2, "Product2", 20.0));
        this.products.add(new Product(3, "Product3", 30.0));
        this.products.add(new Product(4, "Product4", 40.0));
        this.products.add(new Product(5, "Product5", 50.0));
    }

    public List<Product> findAll() {
        return Collections.unmodifiableList(products);
    }

    public Product findById(Integer id) {
        for (Product p : products) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        throw new RuntimeException("Product not found");
    }

    public Product addOrUpdate(Product product) {
        if (product.getId() == null) {
            product.setId(products.size() + 1);
            products.add(product);
            return product;
        } else {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getId().equals(product.getId())) {
                    products.set(i, product);
                    return product;
                }
            }
        }
        throw new RuntimeException("Error add or update product");
    }
}
