package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductTest {
    Product product;

    @BeforeEach
    void setup() {
        this.product = new Product();
        this.product.setId("980f0dd8-e1ce-494f-b1ba-a7e7238c24f6");
        this.product.setProductName("Dummy Product");
        this.product.setProductQuantity(100);
    }

    @Test
    public void testGetProductId() {
        assert(this.product.getId().equals("980f0dd8-e1ce-494f-b1ba-a7e7238c24f6"));
    }

    @Test
    public void testGetProductName() {
        assert(this.product.getProductName().equals("Dummy Product"));
    }

    @Test
    public void testGetProductQuantity() {
        assert(this.product.getProductQuantity() == 100);
    }
}