package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Story("Тесты для сайта wildberries")

public class WildberriesTest {
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriver() {
        driver = new ChromeDriver();
        driver = new EventFiringDecorator(new CustomLogger()).decorate(new ChromeDriver());
    }

    @Test
    @Feature("Выбрать страну")
    void findCountry() throws InterruptedException {
        driver.get("https://www.wildberries.ru");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        new MainPage(driver)
                .hoverCountryMenu()
                .clickCountry();
        Assertions.assertEquals(driver.getCurrentUrl(), "https://by.wildberries.ru/");
    }

    @Test
    @Feature("Ввести в поле адрес")
    void findAddress() throws InterruptedException {
        driver.get("https://www.wildberries.ru/services/besplatnaya-dostavka?desktop=1#terms-delivery");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        new AddressPage(driver)
                .address("Москва")
                .clickAddressButton();
        Assertions.assertTrue(driver.findElement(By.xpath("//div[@class='address-item__info']")).getText().contains("Пункт выдачи"));
    }

    @Test
    @Feature("Добавить товар в корзину")
    void addProductToCart() throws InterruptedException {
        driver.get("https://www.wildberries.ru");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        new MenuBlock(driver)
                .menuClick()
                .productsMenuClick()
                .clickProducts()
                .clickProductsList()
                .selectSize()
                .clickAddToCartButton();
        Assertions.assertEquals(driver.findElement(By.xpath("//span[@class='navbar-pc__notify']")).getText(),"1");

    }
    @AfterEach
    void tearDown() {
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);

        for (LogEntry log : logEntries) {
            Allure.addAttachment("Элемент лога браузера", log.getMessage());
        }
        driver.quit();
    }
}
