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
        product.setId("123e4567-e89b-12d3-a456-556642440000");
        product.setName("Product 1");
        product.setQuantity(100);
        productRepository.create(product);

        Iterator<Product> products = productRepository.findAll();
        assertTrue(products.hasNext());

        Product savedProduct = products.next();

        assertEquals(product.getId(), savedProduct.getId());
        assertEquals(product.getName(), savedProduct.getName());
        assertEquals(product.getQuantity(), savedProduct.getQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> products = productRepository.findAll();
        assertFalse(products.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setId("123e4567-e89b-12d3-a456-556642440000");
        product1.setName("Product 1");
        product1.setQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setId("123e4567-e89b-12d3-a456-556642440001");
        product2.setName("Product 2");
        product2.setQuantity(200);
        productRepository.create(product2);

        Iterator<Product> products = productRepository.findAll();
        assertTrue(products.hasNext());

        Product savedProduct = products.next();
        assertEquals(product1.getId(), savedProduct.getId());

        savedProduct = products.next();
        assertEquals(product2.getId(), savedProduct.getId());

        assertFalse(products.hasNext());
    }


    // Update
    @Test
    void testUpdate() {
        Product product = new Product();
        product.setId("123e4567-e89b-12d3-a456-556642440000");
        product.setName("Product 1");
        product.setQuantity(100);
        productRepository.create(product);

        product.setName("Product 2");
        product.setQuantity(200);
        productRepository.update(product.getId(), product);

        Iterator<Product> products = productRepository.findAll();
        assertTrue(products.hasNext());

        Product savedProduct = products.next();

        assertEquals(product.getId(), savedProduct.getId());
        assertEquals(product.getName(), savedProduct.getName());
        assertEquals(product.getQuantity(), savedProduct.getQuantity());
    }

    @Test
    void testUpdateIfEmpty() {
        Product product = new Product();
        product.setId("123e4567-e89b-12d3-a456-556642440000");
        product.setName("Product 1");
        product.setQuantity(100);
        productRepository.update(product.getId(), product);

        Iterator<Product> products = productRepository.findAll();
        assertFalse(products.hasNext());
    }

    @Test
    void testUpdateIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setId("123e4567-e89b-12d3-a456-556642440000");
        product1.setName("Product 1");
        product1.setQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setId("123e4567-e89b-12d3-a456-556642440001");
        product2.setName("Product 2");
        product2.setQuantity(200);
        productRepository.create(product2);

        product1.setName("Product 3");
        product1.setQuantity(300);
        productRepository.update(product1.getId(), product1);

        Iterator<Product> products = productRepository.findAll();
        assertTrue(products.hasNext());

        Product savedProduct = products.next();
        assertEquals(product1.getId(), savedProduct.getId());

        savedProduct = products.next();
        assertEquals(product2.getId(), savedProduct.getId());

        assertFalse(products.hasNext());
    }

    // Delete
    @Test
    void testDelete() {
        Product product = new Product();
        product.setId("123e4567-e89b-12d3-a456-556642440000");
        product.setName("Product 1");
        product.setQuantity(100);
        productRepository.create(product);

        productRepository.delete(product.getId());

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
        product1.setId("123e4567-e89b-12d3-a456-556642440000");
        product1.setName("Product 1");
        product1.setQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setId("123e4567-e89b-12d3-a456-556642440001");
        product2.setName("Product 2");
        product2.setQuantity(200);
        productRepository.create(product2);

        productRepository.delete(product1.getId());

        Iterator<Product> products = productRepository.findAll();
        assertTrue(products.hasNext());

        Product savedProduct = products.next();
        assertEquals(product2.getId(), savedProduct.getId());

        assertFalse(products.hasNext());
    }

    @Test
    void testFindByIdSuccess() {
        Product product = new Product();
        product.setId("123e4567-e89b-12d3-a456-556642440000");
        product.setName("Product 1");
        product.setQuantity(100);
        productRepository.create(product);

        Product savedProduct = productRepository.findById("123e4567-e89b-12d3-a456-556642440000");

        assertEquals(product, savedProduct);
    }

    @Test
    void testFindByIdNotFound() {
        Product product = new Product();
        product.setId("123e4567-e89b-12d3-a456-556642440000");
        product.setName("Product 1");
        product.setQuantity(100);
        productRepository.create(product);

        Product nonExistent = productRepository.findById("1");

        assertNull(nonExistent);
    }

    @Test
    void testEditSuccess() {
        Product product = new Product();
        product.setId("123e4567-e89b-12d3-a456-556642440000");
        product.setName("Dummy Product");
        product.setQuantity(100);
        productRepository.create(product);

        product.setName("Dummy Yeah");
        product.setQuantity(200);
        Product editedProduct = productRepository.update(product.getId(), product);

        assertNotNull(editedProduct);
        assertEquals("Dummy Yeah", editedProduct.getName());
        assertEquals(200, editedProduct.getQuantity());

        Product edited = productRepository.findById("123e4567-e89b-12d3-a456-556642440000");
        assertEquals("Dummy Yeah", edited.getName());
        assertEquals(200, edited.getQuantity());
        assertEquals(product.getId(), edited.getId());
    }

    @Test
    void testEditNonExistent() {
        Product product = new Product();
        product.setId("123e4567-e89b-12d3-a456-556642440000");
        product.setName("Dummy Product");
        product.setQuantity(100);

        Product result = productRepository.update(product.getId(), product);
        assertNull(result);

        // save the product but don't use the same ID later
        productRepository.create(product);

        // set decoy product
        Product decoy = new Product();
        decoy.setId("decoy");
        decoy.setName("Dummy Yeah");
        decoy.setQuantity(200);

        Product result2 = productRepository.update(decoy.getId(), decoy);

        // make sure no product updated
        assertNull(result2);
    }

    @Test
    void testDeleteSuccess() {
        Product product = new Product();
        product.setId("123e4567-e89b-12d3-a456-556642440000");
        product.setName("Dummy");
        product.setQuantity(100);
        productRepository.create(product);

        Product notSaved = productRepository.findById("123e4567-e89b-12d3-a456-556642440000");
        assertEquals(product.getId(), notSaved.getId());

        productRepository.delete("123e4567-e89b-12d3-a456-556642440000");
        Iterator<Product> afterDelete = productRepository.findAll();
        assertFalse(afterDelete.hasNext());
    }

    @Test
    void testDeleteNonExistent() {
        Product p = new Product();
        p.setId("123e4567-e89b-12d3-a456-556642440000");
        p.setName("Dummy Product");
        p.setQuantity(100);

        // delete nonexistent product
        productRepository.delete("123e4567-e89b-12d3-a456-556642440000");

        // save the product but don't use the same ID later
        productRepository.create(p);

        // set decoy product
        Product decoy = new Product();
        decoy.setId("decoy");
        decoy.setName("Dummy Yeah");
        decoy.setQuantity(200);

        productRepository.delete(decoy.getId());

        Iterator<Product> productIterator = productRepository.findAll();

        // shouldn't be deleted because we were trying to delete the decoy
        assertTrue(productIterator.hasNext());
    }
}
