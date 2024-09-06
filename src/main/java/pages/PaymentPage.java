package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PaymentPage {

    private WebDriver driver;
    private WebDriverWait wait;

   
    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Локаторы
    private By serviceOption = By.xpath("//label[text()='Услуги связи']");
    private By internetOption = By.xpath("//label[text()='Домашний интернет']");
    private By installmentOption = By.xpath("//label[text()='Рассрочка']");
    private By debtOption = By.xpath("//label[text()='Задолженность']");
    public By phoneNumberInput = By.id("phone-number-input");
    private By continueButton = By.xpath("//button[text()='Продолжить']");
    public By cardNumberPlaceholder = By.id("card-number-placeholder");
    public By visaLogo = By.xpath("//img[@alt='Visa']");
    public By masterCardLogo = By.xpath("//img[@alt='MasterCard']");
    private By amountText = By.xpath("//span[@id='amount']");
    private By phoneNumberDisplay = By.xpath("//span[@id='phone-number']");

    // Методы взаимодействия с элементами страницы
    public void selectServiceOption() {
        wait.until(ExpectedConditions.elementToBeClickable(serviceOption)).click();
    }

    public void selectInternetOption() {
        wait.until(ExpectedConditions.elementToBeClickable(internetOption)).click();
    }

    public void selectInstallmentOption() {
        wait.until(ExpectedConditions.elementToBeClickable(installmentOption)).click();
    }

    public void selectDebtOption() {
        wait.until(ExpectedConditions.elementToBeClickable(debtOption)).click();
    }

    public void enterPhoneNumber(String phoneNumber) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(phoneNumberInput)).sendKeys(phoneNumber);
    }

    public void clickContinueButton() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    public String getPlaceholderText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getAttribute("placeholder");
    }

    public boolean isPaymentIconDisplayed(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
    }

    public String getAmountText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(amountText)).getText();
    }

    public String getPhoneNumberDisplayText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(phoneNumberDisplay)).getText();
    }

    public String getContinueButtonText() {
        return wait.until(ExpectedConditions.elementToBeClickable(continueButton)).getText();
    }
}
