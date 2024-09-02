package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private WebDriver driver;

    // Локаторы
    private By paymentBlock = By.xpath("//h2[contains(text(), 'Онлайн пополнение без комиссии')]");
    private By moreInfoLink = By.xpath("//a[contains(text(), 'Подробнее о сервисе')]");

    // Конструктор
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Метод для проверки наличия блока оплаты
    public boolean isPaymentBlockPresent() {
        WebElement element = driver.findElement(paymentBlock);
        return element != null && element.isDisplayed();
    }

    // Метод для клика "Подробнее о сервисе"
    public void clickMoreInfoLink() {
        driver.findElement(moreInfoLink).click();
    }

    // Метод для перехода к странице оплаты
    public PaymentPage openPaymentPage() {
        clickMoreInfoLink();
        return new PaymentPage(driver);
    }
}
