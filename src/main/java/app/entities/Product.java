package app.entities;

import app.dtos.ProductDTO;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_name", nullable = false)
    private String productName;
    @Column(nullable = false)
    private double price;
    @Column(name = "product_number", nullable = true)
    private String productNumber;



    public Product(ProductDTO productDTO){
        this.productName = productDTO.getProductName();
        this.price = productDTO.getPrice();
        this.productNumber = productDTO.getProductNumber();

    }
}
