package Lesson_9;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.HomePage;
import pages.PaymentPage;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MtsByTests {

    private WebDriver driver;
    private HomePage homePage;
    private PaymentPage paymentPage;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");

        driver = new ChromeDriver(options);
        driver.get("https://www.mts.by/");


        homePage = new HomePage(driver);
        paymentPage = new PaymentPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testPaymentBlockPresence() {
        assertTrue(homePage.isPaymentBlockPresent(), "Блок 'Онлайн пополнение без комиссии' не отображается на главной странице");
    }

    @Test
    public void testPlaceholderTexts() {
        homePage.clickMoreInfoLink();  // Переход на страницу оплаты
        assertEquals("Введите номер телефона", paymentPage.getPlaceholderText(By.id("phone-number-input")), "Неправильный placeholder для 'Услуги связи'");
    }

    @Test
    public void testContinueButtonForServices() {
        homePage.clickMoreInfoLink();  // Переход на страницу оплаты
        paymentPage.selectServiceOption();
        paymentPage.enterPhoneNumber("297777777");
        paymentPage.clickContinueButton();

        // Проверка суммы
        assertEquals("10.00 BYN", paymentPage.getAmountText(), "Некорректная сумма");
    }
}
