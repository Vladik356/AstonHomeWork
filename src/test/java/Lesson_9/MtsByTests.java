package Lesson_9;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.PaymentPage;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MtsByTests {

    private WebDriver driver;
    private HomePage homePage;
    private PaymentPage paymentPage;

    @BeforeEach
        public void setUp() {
            System.setProperty("webdriver.chrome.driver", "C:/AstonHW/Lesson/chromedriver.exe");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement acceptCookiesButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'Принять')]")));
            acceptCookiesButton.click();
        } catch (Exception e) {
            System.out.println("Окно куки закрыто");

            WebElement paymentBlock = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(), 'Онлайн пополнение без комиссии')]")));
            assertTrue(homePage.isPaymentBlockPresent(), "Блок 'Онлайн пополнение без комиссии' не отображается на главной странице");
        }
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
