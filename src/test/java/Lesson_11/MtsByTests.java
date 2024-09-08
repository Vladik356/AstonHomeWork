package Lesson_11;

import io.qameta.allure.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.PaymentPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Тестирование MTS.by  ")
@Feature("Функциональность оплаты")

public class MtsByTests {

    private WebDriver driver;
    private HomePage homePage;
    private PaymentPage paymentPage;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/AstonHW/Lesson/chromedriver.exe");
        driver = new ChromeDriver();
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
    @Story("Проверка блока оплаты")
    @Description("Проверяем, что на главной странице присутствует блок 'Онлайн пополнение без комиссии'")
    @Severity(SeverityLevel.CRITICAL)
    public void testCheckPaymentBlockTitle() {
        assertTrue(homePage.isPaymentBlockPresent(), "Блок 'Онлайн пополнение без комиссии' не найден.");
    }

    @Test
    @Story("Проверка плейсхолдеров в полях оплаты")
    @Description("Проверяем плейсхолдеры в полях оплаты для различных типов услуг")
    @Severity(SeverityLevel.NORMAL)
    public void testCheckPlaceholdersForServiceOptions() {
        homePage.openPaymentPage();
        paymentPage.selectServiceOption();

        assertEquals("Номер телефона", paymentPage.getPhoneNumberPlaceholder(), "Неправильный плейсхолдер для поля 'Номер телефона'");
        assertEquals("Сумма", paymentPage.getAmountPlaceholder(), "Неправильный плейсхолдер для поля 'Сумма'");
    }

    @Test
    @Story("Оплата услуги связи")
    @Description("Тестируем процесс оплаты для опции 'Услуги связи'")
    @Severity(SeverityLevel.BLOCKER)
    public void testPaymentForServiceOption() {
        homePage.openPaymentPage();
        paymentPage.selectServiceOption();
        paymentPage.enterPhoneNumber("297777777");
        paymentPage.enterAmount("10");
        paymentPage.clickContinueButton();

        assertTrue(paymentPage.isConfirmationWindowDisplayed(), "Окно подтверждения не отображается.");
        assertEquals("10.00 BYN", paymentPage.getConfirmationAmount(), "Неправильная сумма в окне подтверждения.");
        assertEquals("297777777", paymentPage.getConfirmationPhone(), "Неправильный номер телефона в окне подтверждения.");
        assertEquals("Номер карты", paymentPage.getCardPlaceholder(), "Неправильный плейсхолдер для ввода номера карты.");
        assertTrue(paymentPage.arePaymentIconsDisplayed(), "Иконки платёжных систем не отображаются.");
    }
}
