package app.daos;

import app.dtos.ReceiptDTO;
import app.entities.Receipt;
import app.exceptions.ApiException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ReceiptDAO implements IDAO<ReceiptDTO, Integer>{
    private EntityManagerFactory emf;

    public ReceiptDAO(EntityManagerFactory emf){
        this.emf = emf;
    }


    @Override
    public ReceiptDTO read(Integer integer) {
        try(EntityManager em = emf.createEntityManager()){
            Receipt receipt = em.find(Receipt.class, integer);
            if(receipt == null){
                throw new ApiException(404, "Couldnt find the Trip in the Database");
            }
            return new ReceiptDTO(receipt);
        }
    }

    @Override
    public List<ReceiptDTO> readAll() {
        try(EntityManager em = emf.createEntityManager()){
            TypedQuery<ReceiptDTO> query = em.createQuery("SELECT new app.dtos.ReceiptDTO(r) FROM Receipt r", ReceiptDTO.class);
            List<ReceiptDTO> receipts = query.getResultList();
            if(receipts.isEmpty()){
                throw new ApiException(404,"Could not find any receipt in the database");
            }
            return query.getResultList();
        }
    }

    @Override
    public ReceiptDTO create(ReceiptDTO receiptDTO) {
        Receipt receipt = new Receipt(receiptDTO);
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(receipt);
            em.getTransaction().commit();
        }catch (IllegalArgumentException ex) {
            throw new ApiException(400, "Invalid entity type");
        }
            return new ReceiptDTO(receipt);
    }


    @Override
    public ReceiptDTO update(Integer integer, ReceiptDTO receiptDTO){
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            Receipt receipt = em.find(Receipt.class, integer);
            if(receipt == null){
                throw new ApiException(404,"Guide not found with Id");
            }

            receipt.setName(receiptDTO.getProductName());
            receipt.setPrice(receiptDTO.getPrice());
            receipt.setQuantity(receiptDTO.getQuantity());
            receipt.setStatus(receiptDTO.getStatus());
            receipt.setCreatedAt(receiptDTO.getCreatedAt());
            receipt.setDeliveredAt(receiptDTO.getDeliveredAt());


            Receipt mergedReceipt = em.merge(receipt);
            if(mergedReceipt == null){
                throw new ApiException(400,"Something went wrong with updating the receipt");
            }
            em.getTransaction().commit();
            return new ReceiptDTO(mergedReceipt);

        }
    }

    @Override
    public void delete(Integer integer) {
    try(EntityManager em = emf.createEntityManager()){
        Receipt receipt = em.find(Receipt.class, integer);
        if(receipt == null){
            throw new ApiException(404,"Receipt not found or does not exist in the database");
        }
        em.getTransaction().begin();
        em.remove(receipt);
        em.getTransaction().commit();
        }
    }

}
