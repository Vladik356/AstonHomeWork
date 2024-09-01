package Lesson_8;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class MtsByTests {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://www.mts.by/");
        try {
            WebElement acceptCookiesButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Принять')]")));
            acceptCookiesButton.click();
        } catch (Exception e) {
            System.out.println("Запрос на файлы cookie не найден или уже принят");
        }
    }

    @Test
    public void testBlockTitle() {
        try {
            WebElement blockTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@class='pay']/div/h2[1]")));
            String expectedTitle = "Онлайн пополнение без комиссии";
            String actualTitle = blockTitle.getText();
            Assert.assertEquals(actualTitle, expectedTitle, "Название блока не совпадает!");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Не удалось найти название блока");
        }
    }

    @Test
    public void testPaymentSystemLogos() {
        try {
            WebElement logoVisa = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[contains(@src, 'visa')]")));
            WebElement logoMastercard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[contains(@src, 'mastercard')]")));
            Assert.assertTrue(logoVisa.isDisplayed(), "Логотип Visa не отображается!");
            Assert.assertTrue(logoMastercard.isDisplayed(), "Логотип Mastercard не отображается!");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Не удалось найти логотипы платежных систем");
        }
    }

    @Test
    public void testMoreInfoLink() {
        try {
            WebElement moreInfoLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Подробнее о сервисе')]")));
            moreInfoLink.click();
            wait.until(ExpectedConditions.urlContains("services"));
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("services"), "Ссылка 'Подробнее о сервисе' ведет на неверную страницу!");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Не удалось проверить ссылку 'Подробнее о сервисе'");
        }
    }

    @Test
    public void testContinueButton() {
        try {
            WebElement rechargeSection = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Пополнить счет')]")));
            rechargeSection.click();

            WebElement serviceTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Услуги связи']")));
            serviceTab.click();

            WebElement phoneNumberInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='phoneNumber']")));
            phoneNumberInput.sendKeys("297777777");

            WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Продолжить')]")));
            continueButton.click();

            WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'confirmation-message')]")));
            Assert.assertTrue(confirmationMessage.isDisplayed(), "Сообщение о подтверждении не отображается!");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Не удалось проверить кнопку 'Продолжить'");
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
