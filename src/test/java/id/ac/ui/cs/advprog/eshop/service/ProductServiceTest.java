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
        product.setId("123");
    }

    @Test
    void testCreate() {
        // Setup mock return
        when(productRepository.create(product)).thenReturn(product);

        Product createdProduct = productService.create(product);

        // Verify that productRepository.create only invoked once
        verify(productRepository).create(createdProduct);

        // Verify that the product returned are all the same
        assertEquals(product.getId(), createdProduct.getId());
        assertEquals(product.getProductName(), createdProduct.getProductName());
        assertEquals(product.getProductQuantity(), createdProduct.getProductQuantity());
    }

    @Test
    void testFindAll() {
        // same yea
        Product product2 = new Product();
        product2.setId("124");
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
        when(productRepository.findById(product.getId())).thenReturn(product);

        Product p = productService.findById(product.getId());

        verify(productRepository).findById(p.getId());

        assertEquals(product.getId(), p.getId());
        assertEquals(product.getProductName(), p.getProductName());
        assertEquals(product.getProductQuantity(), p.getProductQuantity());

    }

    @Test
    void testUpdate() {
        when(productRepository.findById(product.getId())).thenReturn(product);
        when(productRepository.update(product.getId(), product)).thenReturn(product);

        productService.update(product.getId(), product);

        verify(productRepository).update(product.getId(), product);

        Product updated = productService.findById(product.getId());

        assertEquals(product.getId(), updated.getId());
        assertEquals(product.getProductName(), updated.getProductName());
        assertEquals(product.getProductQuantity(), updated.getProductQuantity());

    }

    @Test
    void testDelete() {
        doNothing().when(productRepository).delete(product.getId());

        productService.delete(product.getId());

        verify(productRepository).delete(product.getId());
    }
}
