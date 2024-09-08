package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private WebDriver driver;


    private By paymentBlock = By.xpath("//h2[contains(text(), 'Онлайн пополнение без комиссии')]");
    private By moreInfoLink = By.xpath("//a[contains(text(), 'Подробнее о сервисе')]");


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isPaymentBlockPresent() {
        WebElement element = driver.findElement(paymentBlock);
        return element != null && element.isDisplayed();
    }

    public void clickMoreInfoLink() {
        driver.findElement(moreInfoLink).click();
    }

    public PaymentPage openPaymentPage() {
        clickMoreInfoLink();
        return new PaymentPage(driver);
    }
}
