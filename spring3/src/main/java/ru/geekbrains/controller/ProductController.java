package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.model.Product;
import ru.geekbrains.repositories.RepProduct;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private RepProduct repProduct;

    @Autowired
    public void RepProduct(RepProduct repProduct) {
        this.repProduct = repProduct;
    }
    @GetMapping
    public String allProducts(Model model) {
        List<Product> products = repProduct.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/add")
    public String showAdd(Model model) {
        return "add_product";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        repProduct.addOrUpdate(product);
        return "redirect:/products";
    }
}
