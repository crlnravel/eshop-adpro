package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    @Test
    void testGetHome() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void testCreateProductGet() throws Exception {
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("CreateProduct"));
    }

    @Test
    void testCreateProductPost() throws Exception {
        mockMvc.perform(post("/product/create")
                        .param("nameInput", "test")
                        .param("quantityInput", "100"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));
    }

    @Test
    void testProductListGet() throws Exception {

        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("ListProduct"))
                .andExpect(model().attributeExists("products"));
    }

    @Test
    void testProductUpdateGet_NoProduct() throws Exception {
        mockMvc.perform(get("/product/update/123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));
    }

    @Test
    void testProductUpdateGet_Success() throws Exception {
        Product p = new Product();
        p.setId("1");
        p.setName("Dummy Product");
        p.setQuantity(100);

        when(productService.findById("1")).thenReturn(p);

        mockMvc.perform(get("/product/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("EditProduct"));
    }

    @Test
    void testProductUpdatePost() throws Exception {
        Product p = new Product();
        p.setId("1");
        p.setName("Dummy Product");
        p.setQuantity(100);

        when(productService.findById("1")).thenReturn(p);

        mockMvc.perform(post("/product/update/1")
                        .param("nameInput", "test")
                        .param("quantityInput", "100"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));
    }

    @Test
    void testProductDelete() throws Exception {
        mockMvc.perform(post("/product/delete/1")
                        .param("nameInput", "test")
                        .param("quantityInput", "100"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));
    }
}
