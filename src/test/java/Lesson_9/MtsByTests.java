package Lesson_9;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.HomePage;
import pages.PaymentPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        homePage.openPaymentPage();  // Переход на страницу оплаты
        assertEquals("Введите номер телефона", paymentPage.getPlaceholderText(paymentPage.phoneNumberInput), "Неправильный placeholder для 'Услуги связи'");
        paymentPage.selectInternetOption();
        assertEquals("Введите номер лицевого счета", paymentPage.getPlaceholderText(paymentPage.phoneNumberInput), "Неправильный placeholder для 'Домашний интернет'");
        paymentPage.selectInstallmentOption();
        assertEquals("Введите номер договора", paymentPage.getPlaceholderText(paymentPage.phoneNumberInput), "Неправильный placeholder для 'Рассрочка'");
        paymentPage.selectDebtOption();
        assertEquals("Введите номер договора", paymentPage.getPlaceholderText(paymentPage.phoneNumberInput), "Неправильный placeholder для 'Задолженность'");
    }

    @Test
    public void testContinueButtonForServices() {
        homePage.openPaymentPage();  // Переход на страницу оплаты
        paymentPage.selectServiceOption();
        paymentPage.enterPhoneNumber("297777777");
        paymentPage.clickContinueButton();

        // Проверка корректности отображения суммы, номера телефона и плейсхолдера для карты
        assertEquals("10.00 BYN", paymentPage.getAmountText(), "Некорректная сумма");
        assertEquals("297777777", paymentPage.getPhoneNumberDisplayText(), "Некорректный номер телефона");
        assertEquals("Введите номер карты", paymentPage.getPlaceholderText(paymentPage.cardNumberPlaceholder), "Неправильный placeholder для карты");

        // Проверка наличия иконок платёжных систем
        assertTrue(paymentPage.isPaymentIconDisplayed(paymentPage.visaLogo), "Логотип Visa не отображается");
        assertTrue(paymentPage.isPaymentIconDisplayed(paymentPage.masterCardLogo), "Логотип MasterCard не отображается");
    }
}
