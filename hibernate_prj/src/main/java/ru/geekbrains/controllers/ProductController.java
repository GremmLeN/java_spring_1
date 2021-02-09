package ru.geekbrains.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.entity.Product;
import ru.geekbrains.filters.PriceFilter;
import ru.geekbrains.repositoriers.ProductRepository;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/app")
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    @ResponseBody
    public List<Product> getProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    @ResponseBody
    public Optional<Product> getProductById(@PathVariable() Long id) {
        return productRepository.findById(id);
    }

    @PostMapping("/products")
    @ResponseBody
    public void saveProduct(@RequestBody Product product) {
        productRepository.save(product);
    }

    @DeleteMapping("/products/delete/{id}")
    @ResponseBody
    public void deleteProduct(@PathVariable() Long id) {
        productRepository.deleteById(id);
    }

    @GetMapping("/products/pricefilter")
    @ResponseBody
    public List<Product> getProductsByFilter(@RequestBody PriceFilter request) {
        if (request.getMinPrice() != null && request.getMaxPrice() == null) {
            return productRepository.findProductsByPriceAfter(request.getMinPrice());
        } else if (request.getMinPrice() == null && request.getMaxPrice() != null) {
            return productRepository.findProductsByPriceBefore(request.getMaxPrice());
        } else {
            return productRepository.findProductsByPriceBetween(request.getMinPrice(), request.getMaxPrice());
        }
    }

//    @GetMapping("/products/minpricefilter/{minPrice}")
//    @ResponseBody
//    public List<Product> getProductsMinPriceFilter(@PathVariable() Integer minPrice) {
//        return productRepository.findProductsByPriceAfter(minPrice);
//    }
//
//    @GetMapping("/products/maxpricefilter/{maxPrice}")
//    @ResponseBody
//    public List<Product> getProductsMaxPriceFilter(@PathVariable() Integer maxPrice) {
//        return productRepository.findProductsByPriceBefore(maxPrice);
//    }
//
//    @GetMapping("/products/betweenpricefilter/{minPrice}/{maxPrice}")
//    @ResponseBody
//    public List<Product> getProductsBetweenPriceFilter(@PathVariable() Integer minPrice,
//                                                       @PathVariable() Integer maxPrice) {
//        return productRepository.findProductsByPriceBetween(minPrice, maxPrice);
//    }
}
