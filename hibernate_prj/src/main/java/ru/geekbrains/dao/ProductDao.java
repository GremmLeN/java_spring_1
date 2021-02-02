package ru.geekbrains.dao;

import ru.geekbrains.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ProductDao {

    public static boolean EntityManager(EntityManager entityManager, Product product) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(product);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static Product findById(EntityManager entityManager, Long id) {
        return entityManager.find(Product.class, id);
    }

    public static List<Product> findAll(EntityManager entityManager) {
        entityManager.getTransaction().begin();
        List<Product> list = (List<Product>) entityManager.createQuery("SELECT p from Product p")
                .getResultList();
        entityManager.getTransaction().commit();
        return list;
    }

    public static Product saveOrUpdate(EntityManager entityManager, Product product) {

        entityManager.getTransaction().begin();
        Product productFromDb = findById(entityManager, product.getId());
        productFromDb.setTitle(product.getTitle());
        productFromDb.setPrice(product.getPrice());
        entityManager.merge(productFromDb);
        entityManager.getTransaction().commit();

        return findById(entityManager, product.getId());
    }

    public static boolean delete(EntityManager entityManager, Product product) {

        try {
            entityManager.getTransaction().begin();
            Product productFromDb = findById(entityManager, product.getId());
            if (productFromDb.equals(null)) {
                //System.out.println("Product with id not found: " + product.getId());
                return false;
            } else {
                Query query = entityManager.createQuery("DELETE FROM Product p WHERE p.id = ?1");
                query.setParameter(1, productFromDb.getId()).executeUpdate();
                entityManager.getTransaction().commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().commit();
            return false;
        }

    }
}
