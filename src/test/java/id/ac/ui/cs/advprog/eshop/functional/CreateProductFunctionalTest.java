package id.ac.ui.cs.advprog.eshop.functional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.github.bonigarcia.seljup.SeleniumJupiter;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {

    @LocalServerPort
    private int port;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, port);
    }

    @Test
    void createProduct_isCorrect(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/product/create");
        driver.findElement(By.id("nameInput")).sendKeys("Dummy Product");
        driver.findElement(By.id("quantityInput")).clear();
        driver.findElement(By.id("quantityInput")).sendKeys("10");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Should move
        String currentUrl = driver.getCurrentUrl();
        assertEquals(baseUrl + "/product/list", currentUrl);

        String itemName = driver.findElement(By.cssSelector("tr > td:nth-child(1)")).getText();
        String itemQuantity = driver.findElement(By.cssSelector("tr > td:nth-child(2)")).getText();

        assertEquals("Dummy Product", itemName);
        assertEquals("10", itemQuantity);
    }

    @Test
    void createProduct_isEmpty(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/product/create");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Should not move
        String currentUrl = driver.getCurrentUrl();
        assertEquals(baseUrl + "/product/create", currentUrl);
    }

    @Test
    void createProduct_isNotNumber(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/product/create");
        driver.findElement(By.id("nameInput")).sendKeys("Dummy Product");

        // Send not number
        driver.findElement(By.id("quantityInput")).clear();
        driver.findElement(By.id("quantityInput")).sendKeys("abc");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Should not move
        String currentUrl = driver.getCurrentUrl();
        assertEquals(baseUrl + "/product/create", currentUrl);
    }
}