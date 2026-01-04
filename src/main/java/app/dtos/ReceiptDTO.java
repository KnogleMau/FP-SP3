package app.dtos;

import app.entities.Receipt;
import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ReceiptDTO {

    private Integer id;
    private String productName;
    private double price;
    private double quantity;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime deliveredAt;



public ReceiptDTO(Receipt receipt){
    this.id = receipt.getId();
    this.productName = receipt.getName();
    this.price = receipt.getPrice();
    this.quantity = receipt.getQuantity();
    this.status = receipt.getStatus();
    this.createdAt = receipt.getCreatedAt();
    this.deliveredAt = receipt.getDeliveredAt();
}
}
