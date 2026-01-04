package app.entities;


import app.dtos.ReceiptDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder


@Entity
public class Receipt {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false)
  private String  name;
  @Column(nullable = false)
  private double price;
  @Column(nullable = false)
  private double quantity;
  @Column(nullable = false)
  private String status;
  @Column(name = "created_at", nullable = true)
  private LocalDateTime createdAt;
  @Column(name = "delivered_at", nullable = true)
  private LocalDateTime deliveredAt;


  public Receipt(ReceiptDTO receiptDTO){
      this.id = receiptDTO.getId();
      this.name = receiptDTO.getProductName();
      this.price = receiptDTO.getPrice();
      this.quantity = receiptDTO.getQuantity();
      this.status = receiptDTO.getStatus();
      this.createdAt = receiptDTO.getCreatedAt();
      this.deliveredAt = receiptDTO.getDeliveredAt();
  }



}
