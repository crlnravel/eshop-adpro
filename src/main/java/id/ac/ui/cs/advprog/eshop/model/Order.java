package id.ac.ui.cs.advprog.eshop.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class Order extends Item {
    private List<Product> products;
    private Long orderTime;
    private String author;
    @Setter
    private String status;

    public Order(List<Product> products, Long orderTime, String author) {
        super();
    }

    public Order(List<Product> products, Long orderTime, String author, String status) {
        super();
    }
}