package Lesson_9;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {

    private WebDriver driver;


    private By blockTitleLocator = By.xpath("//div[contains(@class, 'section') and .//h2[text()='Онлайн пополнение без комиссии']]//h2");
    private By serviceOptionLocator = By.xpath("//label[text()='Услуги связи']");
    private By internetOptionLocator = By.xpath("//label[text()='Домашний интернет']");
    private By installmentOptionLocator = By.xpath("//label[text()='Рассрочка']");
    private By debtOptionLocator = By.xpath("//label[text()='Задолженность']");
    private By phoneNumberInputLocator = By.id("297777777");
    private By continueButtonLocator = By.xpath("//button[text()='Продолжить']");
    private By cardInputPlaceholderLocator = By.id("card-number-placeholder"); 
    private By paymentIconLocator = By.xpath("//img[@alt='Visa']"); // Например, иконка Visa

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }


    public String getBlockTitle() {
        return driver.findElement(blockTitleLocator).getText();
    }


    public void selectServiceOption() {
        driver.findElement(serviceOptionLocator).click();
    }


    public String getPlaceholderText(By locator) {
        return driver.findElement(locator).getAttribute("placeholder");
    }


    public void enterPhoneNumber(String phoneNumber) {
        driver.findElement(phoneNumberInputLocator).sendKeys(phoneNumber);
    }


    public void clickContinueButton() {
        driver.findElement(continueButtonLocator).click();
    }


    public String getAmountText() {

        WebElement amountElement = driver.findElement(By.id("amount-text")); 
        return amountElement.getText();
    }


    public boolean isPaymentIconDisplayed() {
        return driver.findElement(paymentIconLocator).isDisplayed();
    }


    public String getContinueButtonText() {
        return driver.findElement(continueButtonLocator).getText();
    }
}
