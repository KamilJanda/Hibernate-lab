import org.hibernate.*;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;

import javax.persistence.*;
import javax.persistence.metamodel.EntityType;

import java.util.Map;


public class Main {
    private static SessionFactory sessionFactory = null;

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabaseConfig");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        Supplier sup = new Supplier("Some Company","Wall Street","New York");

        Product prod1 = new Product("budmax",20);
        Product prod2 = new Product("Super product",40);
        Product prod3 = new Product("bad product",10);
        Product prod4 = new Product("super cool product",40);

        Category cat1 = new Category("category 1");
        Category cat2 = new Category("category 2");
        Category cat3 = new Category("category 3");

        sup.addProduct(prod1);
        sup.addProduct(prod2);
        sup.addProduct(prod3);
        sup.addProduct(prod4);

        cat1.addProducts(prod1);
        cat1.addProducts(prod3);

        cat2.addProducts(prod4);
        cat2.addProducts(prod2);

        entityManager.persist(prod1);
        entityManager.persist(prod2);
        entityManager.persist(prod3);
        entityManager.persist(prod4);


        entityManager.persist(cat1);
        entityManager.persist(cat2);
        entityManager.persist(cat3);

        entityManager.persist(sup);

        entityTransaction.commit();

        TypedQuery<Category> categoryQuery = entityManager.createQuery(
                "From Category where name=:categoryName",Category.class);

        categoryQuery.setParameter("categoryName","category 1");

        Category category1 = categoryQuery.getSingleResult();

        category1.getProducts().forEach(product -> System.out.println(product));

        entityManager.close();
    }

    private static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            sessionFactory = configuration.configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}