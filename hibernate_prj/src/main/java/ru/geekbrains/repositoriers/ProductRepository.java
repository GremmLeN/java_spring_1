package ru.geekbrains.repositoriers;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.entity.Product;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findProductsByPriceAfter(Integer minPrice);

    List<Product> findProductsByPriceBefore(Integer maxPrice);

    List<Product> findProductsByPriceBetween(Integer minPrice, Integer maxPrice);
}
