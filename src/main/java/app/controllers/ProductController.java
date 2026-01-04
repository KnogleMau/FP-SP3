package app.controllers;

import app.config.HibernateConfig;
import app.daos.ProductDAO;
import app.dtos.ProductDTO;
import app.dtos.ReceiptDTO;
import app.exceptions.ApiException;
import io.javalin.http.Context;
import jakarta.persistence.EntityManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ProductController implements IController{
    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
    ProductDAO p = new ProductDAO(emf);

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private static final Logger debugLogger = LoggerFactory.getLogger("app");

    @Override
    public void read(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            ProductDTO productDTO = p.read(id);

            ctx.status(200).json(productDTO);
        } catch(ApiException ex){
            ctx.status(ex.getCode()).result(ex.getMessage());
        }
    }

    @Override
    public void readAll(Context ctx) {

        try {
            List<ProductDTO> trips = p.readAll();
            ctx.status(200).json(trips);
            } catch (ApiException ex){
            ctx.status(404).result("Could not find the trips " + ex.getMessage());
        }
    }


    @Override
    public void create(Context ctx) {
        try {
            ProductDTO productDTO = ctx.bodyAsClass(ProductDTO.class);
            p.create(productDTO);
            ctx.status(201).json(productDTO);
        }catch (ApiException ex) {
            ctx.status(ex.getCode()).result(ex.getMessage());
        }
    }

    @Override
    public void update(Context ctx) {
        try{
            int id = Integer.parseInt(ctx.pathParam("id"));
            ProductDTO productDTO = ctx.bodyAsClass(ProductDTO.class);
            ProductDTO updatedTrip = p.update(id, productDTO);
            ctx.status(200).json(updatedTrip);
        } catch (ApiException ex){
             ctx.status(ex.getCode()).result(ex.getMessage());
        }

    }

    @Override
    public void delete(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        p.delete(id);
        ctx.status(200).result("The Product with the "+ id + " have been deleted");
    }
}
