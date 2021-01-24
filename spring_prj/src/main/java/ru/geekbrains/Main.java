package ru.geekbrains;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.geekbrains.Repository.RepProduct;
import ru.geekbrains.Service.CartService;
import ru.geekbrains.config.AppConfig;

public class Main {

    public static void main(String[] args) {


        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        RepProduct RepProduct = context.getBean("repProduct", RepProduct.class);
        CartService cartService = context.getBean("cartService", CartService.class);
        // Список продуктов
        System.out.println("Список: " + RepProduct.allPrice());
        // Добавляем продукты в корзину
        cartService.addProduct(1);
        cartService.addProduct(3);
        cartService.addProduct(5);
        System.out.println("До изменения корзины");
        System.out.println("В корзине: " + cartService.getProductsInCart());
        System.out.println("Стоимость продуктов: " + cartService.getFullPrice());
        // Изменяем корзину
        cartService.addProduct(5);
        cartService.removeProduct(3);
        System.out.println("После изменения корзины");
        System.out.println("В корзине: " + cartService.getProductsInCart());
        System.out.println("Стоимость продуктов: " + cartService.getFullPrice());

    }
}
