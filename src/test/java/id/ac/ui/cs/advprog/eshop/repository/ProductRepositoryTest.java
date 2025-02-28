package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepositoryImpl productRepository;

    @BeforeEach
    void setUp() {}

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("123e4567-e89b-12d3-a456-556642440000");
        product.setProductName("Product 1");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> products = productRepository.findAll();
        assertTrue(products.hasNext());

        Product savedProduct = products.next();

        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> products = productRepository.findAll();
        assertFalse(products.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("123e4567-e89b-12d3-a456-556642440000");
        product1.setProductName("Product 1");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("123e4567-e89b-12d3-a456-556642440001");
        product2.setProductName("Product 2");
        product2.setProductQuantity(200);
        productRepository.create(product2);

        Iterator<Product> products = productRepository.findAll();
        assertTrue(products.hasNext());

        Product savedProduct = products.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());

        savedProduct = products.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());

        assertFalse(products.hasNext());
    }


    // Update
    @Test
    void testUpdate() {
        Product product = new Product();
        product.setProductId("123e4567-e89b-12d3-a456-556642440000");
        product.setProductName("Product 1");
        product.setProductQuantity(100);
        productRepository.create(product);

        product.setProductName("Product 2");
        product.setProductQuantity(200);
        productRepository.update(product);

        Iterator<Product> products = productRepository.findAll();
        assertTrue(products.hasNext());

        Product savedProduct = products.next();

        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testUpdateIfEmpty() {
        Product product = new Product();
        product.setProductId("123e4567-e89b-12d3-a456-556642440000");
        product.setProductName("Product 1");
        product.setProductQuantity(100);
        productRepository.update(product);

        Iterator<Product> products = productRepository.findAll();
        assertFalse(products.hasNext());
    }

    @Test
    void testUpdateIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("123e4567-e89b-12d3-a456-556642440000");
        product1.setProductName("Product 1");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("123e4567-e89b-12d3-a456-556642440001");
        product2.setProductName("Product 2");
        product2.setProductQuantity(200);
        productRepository.create(product2);

        product1.setProductName("Product 3");
        product1.setProductQuantity(300);
        productRepository.update(product1);

        Iterator<Product> products = productRepository.findAll();
        assertTrue(products.hasNext());

        Product savedProduct = products.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());

        savedProduct = products.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());

        assertFalse(products.hasNext());
    }

    // Delete
    @Test
    void testDelete() {
        Product product = new Product();
        product.setProductId("123e4567-e89b-12d3-a456-556642440000");
        product.setProductName("Product 1");
        product.setProductQuantity(100);
        productRepository.create(product);

        productRepository.delete(product.getProductId());

        Iterator<Product> products = productRepository.findAll();
        assertFalse(products.hasNext());
    }

    @Test
    void testDeleteIfEmpty() {
        productRepository.delete("123e4567-e89b-12d3-a456-556642440000");

        Iterator<Product> products = productRepository.findAll();
        assertFalse(products.hasNext());
    }

    @Test
    void testDeleteIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("123e4567-e89b-12d3-a456-556642440000");
        product1.setProductName("Product 1");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("123e4567-e89b-12d3-a456-556642440001");
        product2.setProductName("Product 2");
        product2.setProductQuantity(200);
        productRepository.create(product2);

        productRepository.delete(product1.getProductId());

        Iterator<Product> products = productRepository.findAll();
        assertTrue(products.hasNext());

        Product savedProduct = products.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());

        assertFalse(products.hasNext());
    }

    @Test
    void testFindByIdSuccess() {
        Product product = new Product();
        product.setProductId("123e4567-e89b-12d3-a456-556642440000");
        product.setProductName("Product 1");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product savedProduct = productRepository.findById("123e4567-e89b-12d3-a456-556642440000");

        assertEquals(product, savedProduct);
    }

    @Test
    void testFindByIdNotFound() {
        Product product = new Product();
        product.setProductId("123e4567-e89b-12d3-a456-556642440000");
        product.setProductName("Product 1");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product nonExistent = productRepository.findById("1");

        assertNull(nonExistent);
    }

    @Test
    void testEditSuccess() {
        Product product = new Product();
        product.setProductId("123e4567-e89b-12d3-a456-556642440000");
        product.setProductName("Dummy Product");
        product.setProductQuantity(100);
        productRepository.create(product);

        product.setProductName("Dummy Yeah");
        product.setProductQuantity(200);
        Product editedProduct = productRepository.update(product);

        assertNotNull(editedProduct);
        assertEquals("Dummy Yeah", editedProduct.getProductName());
        assertEquals(200, editedProduct.getProductQuantity());

        Product edited = productRepository.findById("123e4567-e89b-12d3-a456-556642440000");
        assertEquals("Dummy Yeah", edited.getProductName());
        assertEquals(200, edited.getProductQuantity());
        assertEquals(product.getProductId(), edited.getProductId());
    }

    @Test
    void testEditNonExistent() {
        Product product = new Product();
        product.setProductId("123e4567-e89b-12d3-a456-556642440000");
        product.setProductName("Dummy Product");
        product.setProductQuantity(100);

        Product result = productRepository.update(product);
        assertNull(result);

        // save the product but don't use the same ID later
        productRepository.create(product);

        // set decoy product
        Product decoy = new Product();
        decoy.setProductId("decoy");
        decoy.setProductName("Dummy Yeah");
        decoy.setProductQuantity(200);

        Product result2 = productRepository.update(decoy);

        // make sure no product updated
        assertNull(result2);
    }

    @Test
    void testDeleteSuccess() {
        Product product = new Product();
        product.setProductId("123e4567-e89b-12d3-a456-556642440000");
        product.setProductName("Dummy");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product notSaved = productRepository.findById("123e4567-e89b-12d3-a456-556642440000");
        assertEquals(product.getProductId(), notSaved.getProductId());

        productRepository.delete("123e4567-e89b-12d3-a456-556642440000");
        Iterator<Product> afterDelete = productRepository.findAll();
        assertFalse(afterDelete.hasNext());
    }

    @Test
    void testDeleteNonExistent() {
        Product p = new Product();
        p.setProductId("123e4567-e89b-12d3-a456-556642440000");
        p.setProductName("Dummy Product");
        p.setProductQuantity(100);

        // delete nonexistent product
        productRepository.delete("123e4567-e89b-12d3-a456-556642440000");

        // save the product but don't use the same ID later
        productRepository.create(p);

        // set decoy product
        Product decoy = new Product();
        decoy.setProductId("decoy");
        decoy.setProductName("Dummy Yeah");
        decoy.setProductQuantity(200);

        productRepository.delete(decoy.getProductId());

        Iterator<Product> productIterator = productRepository.findAll();

        // shouldn't be deleted because we were trying to delete the decoy
        assertTrue(productIterator.hasNext());
    }
}
