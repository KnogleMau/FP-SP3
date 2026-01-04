package app.daos;

import app.dtos.ProductDTO;
import app.entities.Receipt;
import app.entities.Product;
import app.enums.Category;
import app.exceptions.ApiException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ProductDAO implements IDAO<ProductDTO, Integer>{
    private EntityManagerFactory emf;

    public ProductDAO(EntityManagerFactory emf){
    this.emf = emf;
    }
    @Override
    public ProductDTO read(Integer integer) {
        try(EntityManager em = emf.createEntityManager()){
            Product product = em.find(Product.class, integer);
           if(product == null){
               throw new ApiException(404, "Couldnt find the product in the Database");
           }
            return new ProductDTO(product);
        }
    }

    @Override
    public List<ProductDTO> readAll() {
        try(EntityManager em = emf.createEntityManager()){
            TypedQuery<ProductDTO>query = em.createQuery("SELECT new app.dtos.ProductDTO(p) FROM Product p", ProductDTO.class);

            List<ProductDTO> products = query.getResultList();
            if(products.isEmpty()){
                throw new ApiException(404,"Could not find any products in the database");
            }
            return products;
        }
    }

    @Override
    public ProductDTO create(ProductDTO productDTO) {
        Product product = new Product(productDTO);
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(product);
            em.getTransaction().commit();
        }catch (IllegalArgumentException ex) {
            throw new ApiException(400, "Invalid entity type");
        }
        return new ProductDTO(product);
    }

    @Override
    public ProductDTO update(Integer integer, ProductDTO productDTO) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            Product product = em.find(Product.class, integer);
            if(product == null){
                throw new ApiException(404,"Product not found with the given");
            }

            product.setProductName(productDTO.getProductName());
            product.setPrice(productDTO.getPrice());
            product.setProductNumber(productDTO.getProductNumber());

            Product mergedProduct = em.merge(product);

            if(mergedProduct == null){
                throw new ApiException(400,"Something went wrong with updating the Product");
            }
            em.getTransaction().commit();


            return new ProductDTO(mergedProduct);
        }
    }

    @Override
    public void delete(Integer integer) {
try(EntityManager em = emf.createEntityManager()){
    Product product = em.find(Product.class, integer);
    if(product == null){
        throw new ApiException(404,"Product not found and does not exist in the database");
    }
    em.getTransaction().begin();
    em.remove(product);
    em.getTransaction().commit();
    }
}
}
