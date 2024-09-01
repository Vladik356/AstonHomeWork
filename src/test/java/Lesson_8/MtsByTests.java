package Lesson_8;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MtsByTests {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "C:/AstonHW/Lesson/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.mts.by/");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testBlockTitle() {

        WebElement blockTitle = driver.findElement(By.xpath("//div[contains(@class, 'section') and .//h2[text()='Онлайн пополнение без комиссии']]//h2"));
        assertEquals("Онлайн пополнение без комиссии", blockTitle.getText(), "Название блока не совпадает");
    }

    @Test
    public void testPaymentSystemLogos() {

        WebElement visaLogo = driver.findElement(By.xpath("//img[@alt='Visa']"));
        WebElement masterCardLogo = driver.findElement(By.xpath("//img[@alt='MasterCard']"));
        assertTrue(visaLogo.isDisplayed(), "Логотип Visa не отображается");
        assertTrue(masterCardLogo.isDisplayed(), "Логотип MasterCard не отображается");
    }

    @Test
    public void testMoreInfoLink() {

        WebElement moreInfoLink = driver.findElement(By.xpath("//a[text()='Подробнее о сервисе']"));
        moreInfoLink.click();
        String expectedUrl = "https://www.mts.by/bonus/mts_bonus/"; 
        assertEquals(expectedUrl, driver.getCurrentUrl(), "Неверный URL после перехода по ссылке 'Подробнее о сервисе'");
    }

    @Test
    public void testContinueButtonForServices() {

        WebElement servicesOption = driver.findElement(By.xpath("//label[text()='Услуги связи']"));
        servicesOption.click();


        WebElement phoneNumberInput = driver.findElement(By.id("phone-number-input"));
        phoneNumberInput.sendKeys("297777777");


        WebElement continueButton = driver.findElement(By.xpath("//button[text()='Продолжить']"));
        assertTrue(continueButton.isEnabled(), "Кнопка 'Продолжить' не активна после ввода данных");


        continueButton.click();

    }
}
