package Lesson_9;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MtsByTests extends BaseTest {

    @Test
    public void testBlockTitle() {
        MainPage mainPage = new MainPage(driver);
        String blockTitle = mainPage.getBlockTitle();
        assertEquals("Онлайн пополнение без комиссии", blockTitle, "Название блока не совпадает");
    }

    @Test
    public void testPlaceholders() {
        MainPage mainPage = new MainPage(driver);

        mainPage.selectServiceOption();
        String servicePlaceholder = mainPage.getPlaceholderText(By.id("phone-number-input")); // Замените на актуальный ID
        assertEquals("Введите номер телефона", servicePlaceholder, "Неверный текст placeholder для 'Услуги связи'");


        mainPage.selectServiceOption();
        String internetPlaceholder = mainPage.getPlaceholderText(By.id("internet-input"));
        assertEquals("Введите номер лицевого счета", internetPlaceholder, "Неверный текст placeholder для 'Домашний интернет'");


    }

    @Test
    public void testContinueButtonAndSummary() {
        MainPage mainPage = new MainPage(driver);


        mainPage.selectServiceOption();
        mainPage.enterPhoneNumber("297777777");
        mainPage.clickContinueButton();


        String amountText = mainPage.getAmountText();
        assertEquals("Сумма: 10 BYN", amountText, "Сумма отображается некорректно");

        String buttonText = mainPage.getContinueButtonText();
        assertTrue(buttonText.contains("10 BYN"), "Текст на кнопке отображается некорректно");


        String cardPlaceholder = mainPage.getPlaceholderText(By.id("card-number-placeholder"));
        assertEquals("Введите номер карты", cardPlaceholder, "Неверный текст placeholder для поля карты");


        assertTrue(mainPage.isPaymentIconDisplayed(), "Иконка платёжной системы не отображается");
    }
}
