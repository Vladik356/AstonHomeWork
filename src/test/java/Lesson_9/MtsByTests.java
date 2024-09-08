package Lesson_9;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.PaymentPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MtsByTests {

    private WebDriver driver;
    private WebDriverWait wait;
    private HomePage homePage;
    private PaymentPage paymentPage;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
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
        try {
            // Ожидание куки
            WebElement acceptCookiesButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='cookie-agree']")));
            acceptCookiesButton.click();
        } catch (Exception e) {
            System.out.println("Окно куки закрыто или кнопка не найдена.");
        }

        // Ожидание блока оплаты
        WebElement paymentBlock = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='pay-section']/div/div/div[2]/section/div/h2")));
        assertTrue(paymentBlock.isDisplayed(), "Блок 'Онлайн пополнение без комиссии' не найден.");
    }

    @Test
    public void testPlaceholderTexts() {
        homePage.clickMoreInfoLink();  // Переход на страницу оплаты

        WebElement phoneNumberField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("connection-phone")));
        assertEquals("Номер телефона", phoneNumberField.getAttribute("placeholder"), "Неправильный плейсхолдер для 'Услуги связи'");
    }

    @Test
    public void testContinueButtonForServices() {
        homePage.clickMoreInfoLink();  // Переход на страницу оплаты
        paymentPage.selectServiceOption();

        // Ожидание поля ввода номера телефона
        WebElement phoneNumberField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("phone")));
        phoneNumberField.sendKeys("297777777");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement continueButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='pay-connection']/button")));
        assertTrue(continueButton.isDisplayed(), "Кнопка 'Продолжить' не найдена.");

        // Ожидание и проверка суммы
        WebElement amountElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("amount")));
        assertEquals("10.00 BYN", amountElement.getText(), "Некорректная сумма");
    }
}
