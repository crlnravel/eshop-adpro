package id.ac.ui.cs.advprog.eshop.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Builder
@Getter
@Setter
public class Payment extends Item {
    Order order;
    String method;
    String status;
    Map<String, String> paymentData;

    public Payment(Order order, String method, String status, Map<String, String> paymentData) {
        super();
    }
}