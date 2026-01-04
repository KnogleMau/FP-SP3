package app.populate;

import app.daos.ProductDAO;
import app.daos.ReceiptDAO;
import app.entities.Receipt;
import app.entities.Product;
import app.enums.Category;
import app.exceptions.EntityNotFoundException;
import app.security.daos.SecurityDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Populate {

    private EntityManagerFactory emf;
    public Populate(EntityManagerFactory emf){
        this.emf = emf;
    }

    ReceiptDAO receiptDAO = new ReceiptDAO(emf);
    ProductDAO productDAO = new ProductDAO(emf);

    public void populateData() throws EntityNotFoundException {
        Receipt g1 = Receipt.builder()

                .build();



        Product product1 = Product.builder()
                .productName("Iphone 17")
                .productNumber("21211221")
                .price(9959)
                .build();

        Product product2 = Product.builder()
                .productName("Logitech Keyboard")
                .productNumber("72728142")
                .price(1399)
                .build();
        Product product3 = Product.builder()
                .productName("MacBook Pro M5")
                .productNumber("91635275")
                .price(18999)
                .build();

        Product product4 = Product.builder()
                .productName("Logitech Mus")
                .productNumber("72725614")
                .price(249)
                .build();

        Product product5 = Product.builder()
                .productName("Trapper Mouse")
                .productNumber("87652412")
                .price(1299)
                .build();


       try(EntityManager em = emf.createEntityManager()){
           em.getTransaction().begin();
           em.persist(product1);
           em.persist(product2);
           em.persist(product3);
           em.persist(product4);
           em.persist(product5);
           em.getTransaction().commit();

       }

        SecurityDAO securityDAO = new SecurityDAO(emf);

        securityDAO.createRole("User");
        securityDAO.createRole("Anyone");
        securityDAO.createRole("Admin");

        securityDAO.createUser("ADMIN", "1234");
        securityDAO.addUserRole("ADMIN", "Admin");



    }
}
