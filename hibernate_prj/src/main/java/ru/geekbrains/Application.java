package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.dao.ProductDao;
import ru.geekbrains.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Application {
    public static void main(String[] args) {
        EntityManagerFactory factory = new Configuration()
                .configure("hibernate.xml")
                .buildSessionFactory();

        EntityManager manager = factory.createEntityManager();

        Product product1 = new Product("Product1", 20);
        Product product2 = new Product("Product2" , 40);
        Product product3 = new Product("Product3",60);

        System.out.println("Add to DB");
        ProductDao.EntityManager(manager, product1);
        ProductDao.EntityManager(manager, product2);
        ProductDao.EntityManager(manager, product3);

        product1.setId(4L);
        product2.setId(5L);
        product3.setId(6L);

        System.out.println("");
        ProductDao.findAll(manager).forEach(product -> System.out.println(product.toString()));
        System.out.println("");
        System.out.println("");
        System.out.println("Delete Product3");

        ProductDao.delete(manager, product3);
        System.out.println("");
        ProductDao.findAll(manager).forEach(product -> System.out.println(product.toString()));

        product1.setPrice(100);
        product2.setTitle("New Product2 title");
        System.out.println("");
        System.out.println("");
        System.out.println("Change cost on Product1 and name on Product2");
        ProductDao.saveOrUpdate(manager, product1);
        ProductDao.saveOrUpdate(manager, product2);

        ProductDao.findAll(manager).forEach(product -> System.out.println(product.toString()));
    }
}
