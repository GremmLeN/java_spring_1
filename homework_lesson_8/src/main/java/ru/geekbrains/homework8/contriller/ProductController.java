package ru.geekbrains.homework8.contriller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.homework8.entity.Product;
import ru.geekbrains.homework8.repositories.ProductRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/app")
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products/")
    public String getProducts(Model model){
        model.addAttribute("products", productRepository.findAll());
        return "products";
    }

    @DeleteMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id ) {
        productRepository.deleteById(id);
        return "redirect:/products/";
    }

    @GetMapping("/listProduts")
    public String listProdutcs(Model model,
                               @RequestParam("page") Optional<Integer> page,
                               @RequestParam("size") Optional<Integer> size
                               ) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);

        Page<Product> productsPage = productRepository.findAll(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("productsPage", productsPage);

        int totalpages = productsPage.getTotalPages();

        if (totalpages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalpages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "productspag";
    }
}