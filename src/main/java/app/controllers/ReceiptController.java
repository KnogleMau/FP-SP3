package app.controllers;

import app.config.HibernateConfig;
import app.daos.ReceiptDAO;
import app.dtos.ReceiptDTO;
import app.exceptions.ApiException;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import jakarta.persistence.EntityManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ReceiptController implements IController{
    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
    ReceiptDAO r = new ReceiptDAO(emf);

    private static final Logger logger = LoggerFactory.getLogger(ReceiptController.class);
    private static final Logger debugLogger = LoggerFactory.getLogger("app");

    @Override
    public void read(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));

            ReceiptDTO receiptDTO = r.read(id);

            ctx.status(200).json(receiptDTO);
        } catch(ApiException ex){
            System.out.println(ex.getMessage());
            ctx.status(404).result("Could not find the Guide with the given id" + ex.getMessage());
        }
    }

    @Override
    public void readAll(Context ctx) {

        try {
            List<ReceiptDTO> receipts = r.readAll();
            ctx.status(200).json(receipts);
        } catch (ApiException ex){
            ctx.status(404).result("Could not find any of the guides " + ex.getMessage());
        }
    }


    @Override
    public void create(Context ctx) {
        try {
            ReceiptDTO receiptDTO = ctx.bodyAsClass(ReceiptDTO.class);
            r.create(receiptDTO);
            ctx.status(201).json(receiptDTO);
        }catch (BadRequestResponse ex) {
            ctx.status(400).result("Error: Invalid request body: " + ex.getMessage());
        }
    }

    @Override
    public void update(Context ctx) {
        try{
            int id = Integer.parseInt(ctx.pathParam("id"));
            ReceiptDTO receiptDTO = ctx.bodyAsClass(ReceiptDTO.class);
            ReceiptDTO updatedGuide = r.update(id, receiptDTO);
            ctx.status(200).json(updatedGuide);
        } catch (ApiException ex){
            ctx.status(ex.getCode()).result(ex.getMessage());
        }

    }

    @Override
    public void delete(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        r.delete(id);
        ctx.status(204).result("The guide with the "+ id + " have been deleted");
    }
}
