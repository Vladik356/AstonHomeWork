package Lesson_8;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MtsByTests {

    private WebDriver driver;

    @BeforeAll
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://www.mts.by/");
    }

    @AfterAll
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Проверка названия блока 'Онлайн пополнение без комиссии'")
    public void testCheckBlockTitle() {
        WebElement blockTitle = driver.findElement(By.xpath("//h2[contains(text(), 'Онлайн пополнение без комиссии')]"));
        assertTrue(blockTitle.isDisplayed(), "Название блока 'Онлайн пополнение без комиссии' отсутствует.");
    }

    @Test
    @DisplayName("Проверка наличия логотипов платёжных систем")
    public void testCheckPaymentSystemLogos() {
        List<WebElement> paymentLogos = driver.findElements(By.xpath("//div[contains(@class, 'payment-systems')]//img"));
        assertTrue(paymentLogos.size() > 0, "Логотипы платёжных систем не найдены.");
    }

    @Test
    @DisplayName("Проверка работы ссылки 'Подробнее о сервисе'")
    public void testCheckMoreInfoLink() {
        WebElement moreInfoLink = driver.findElement(By.xpath("//a[contains(text(), 'Подробнее о сервисе')]"));
        assertTrue(moreInfoLink.isDisplayed(), "Ссылка 'Подробнее о сервисе' не отображается.");
        moreInfoLink.click();
        String expectedUrl = "https://www.mts.by/services/payments/paycard/";
        assertEquals(expectedUrl, driver.getCurrentUrl(), "Ссылка 'Подробнее о сервисе' ведет на неверный URL.");
        driver.navigate().back();  // Вернуться назад после проверки
    }

    @Test
    @DisplayName("Проверка работы кнопки 'Продолжить' при вводе номера телефона")
    public void testCheckContinueButton() {
        WebElement phoneNumberField = driver.findElement(By.xpath("//input[@name='phone']"));
        phoneNumberField.sendKeys("297777777");

        WebElement continueButton = driver.findElement(By.xpath("//button[contains(text(), 'Продолжить')]"));
        continueButton.click();

        WebElement errorMessage = driver.findElement(By.xpath("//div[@class='error-message']"));
        assertTrue(errorMessage.isDisplayed(), "Не появилось сообщение об ошибке при нажатии на 'Продолжить'.");
    }
}
