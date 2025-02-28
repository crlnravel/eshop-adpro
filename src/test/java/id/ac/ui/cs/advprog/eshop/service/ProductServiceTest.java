package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    // Setup mock for product repository used by product service
    @Mock
    private ProductRepositoryImpl productRepository;

    // Setup product service
    @InjectMocks
    private ProductServiceImpl productService;

    // Setup mock object
    private Product product;

    @BeforeEach
    void setUp() {
        // Setup dummy product
        product = new Product();
        product.setProductName("Dummy Product");
        product.setProductQuantity(100);
        product.setProductId("123");
    }

    @Test
    void testCreate() {
        // Setup mock return
        when(productRepository.create(product)).thenReturn(product);

        Product createdProduct = productService.create(product);

        // Verify that productRepository.create only invoked once
        verify(productRepository).create(createdProduct);

        // Verify that the product returned are all the same
        assertEquals(product.getProductId(), createdProduct.getProductId());
        assertEquals(product.getProductName(), createdProduct.getProductName());
        assertEquals(product.getProductQuantity(), createdProduct.getProductQuantity());
    }

    @Test
    void testFindAll() {
        // same yea
        Product product2 = new Product();
        product2.setProductId("124");
        product2.setProductName("Dummy Yeah");
        product2.setProductQuantity(200);

        // setup mock behaviour
        when(productRepository.findAll()).thenReturn(Arrays.asList(product, product2).iterator());

        // call
        List<Product> products = productService.findAll();

        // verify
        verify(productRepository).findAll();

        assertEquals(2, products.size());
    }

    @Test
    void testFindById() {
        when(productRepository.findById(product.getProductId())).thenReturn(product);

        Product p = productService.findById(product.getProductId());

        verify(productRepository).findById(p.getProductId());

        assertEquals(product.getProductId(), p.getProductId());
        assertEquals(product.getProductName(), p.getProductName());
        assertEquals(product.getProductQuantity(), p.getProductQuantity());

    }

    @Test
    void testUpdate() {
        when(productRepository.update(product)).thenReturn(product);

        Product updated = productService.update(product);

        verify(productRepository).update(product);

        assertEquals(product.getProductId(), updated.getProductId());
        assertEquals(product.getProductName(), updated.getProductName());
        assertEquals(product.getProductQuantity(), updated.getProductQuantity());

    }

    @Test
    void testDelete() {
        doNothing().when(productRepository).delete(product.getProductId());

        productService.delete(product.getProductId());

        verify(productRepository).delete(product.getProductId());
    }
}
