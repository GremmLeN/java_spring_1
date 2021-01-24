package ru.geekbrains.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.geekbrains.Product;
import ru.geekbrains.Repository.RepProduct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class CartService {

    private RepProduct RepProduct;
    private List<Product> productsInCart = new ArrayList<>();

    @Autowired
    public void setRepProduct(RepProduct RepoProduct) {
        this.RepProduct = RepoProduct;
    }

    public List<Product> getProductsInCart() {
        return Collections.unmodifiableList(productsInCart);
    }

    public void addProduct(Integer id) {
        productsInCart.add(RepProduct.productById(id));
    }

    public void removeProduct(Integer id) {
        productsInCart.remove(RepProduct.productById(id));
    }

    public Double getFullPrice() {
        return productsInCart.stream().map(Product::getPrice).reduce(0.0, Double::sum);
    }
}
