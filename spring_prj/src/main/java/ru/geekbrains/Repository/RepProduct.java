package ru.geekbrains.Repository;


import org.springframework.stereotype.Component;
import ru.geekbrains.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class RepProduct {

    private List<Product> productList = new ArrayList<>();

    @PostConstruct
    public void init() {
        productList.add(new Product(1, "Product1", 10.0));
        productList.add(new Product(2, "Product2", 20.0));
        productList.add(new Product(3, "Product3", 30.0));
        productList.add(new Product(4, "Product4", 40.0));
        productList.add(new Product(5, "Product5", 50.0));
    }

    public List<Product> allPrice() {
        return Collections.unmodifiableList(productList);
    }

    public Product productById(Integer id) {
        for (Product p : productList) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }
}
