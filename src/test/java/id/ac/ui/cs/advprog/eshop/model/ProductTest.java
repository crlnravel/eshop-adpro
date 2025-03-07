package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductTest {
    Product product;

    @BeforeEach
    void setup() {
        this.product = new Product();
        this.product.setId("980f0dd8-e1ce-494f-b1ba-a7e7238c24f6");
        this.product.setName("Dummy Product");
        this.product.setQuantity(100);
    }

    @Test
    public void testGetProductId() {
        assert(this.product.getId().equals("980f0dd8-e1ce-494f-b1ba-a7e7238c24f6"));
    }

    @Test
    public void testGetProductName() {
        assert(this.product.getName().equals("Dummy Product"));
    }

    @Test
    public void testGetProductQuantity() {
        assert(this.product.getQuantity() == 100);
    }
}