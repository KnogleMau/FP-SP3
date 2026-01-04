package app.dtos;

import app.entities.Product;
import app.enums.Category;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ProductDTO {

    private Integer id;
    private String productName;
    private double price;
    private String productNumber;

    public ProductDTO(Product product){
        this.id = product.getId();
        this.productName = product.getProductName();
        this.price = product.getPrice();
        this.productNumber = product.getProductNumber();

    }
}
