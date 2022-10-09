package ru.yandex.scooter;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;

public class Order {
     @Test
     public void testOrder() {
         By openFormButton = By.xpath("//button[contains(text(), 'Заказать')]");
         By orderFormNextButton = By.xpath("//button[contains(text(), 'Далее')]");
         By nameField = By.xpath("//input[@placeholder='* Имя']");
         By surNameField = By.xpath("//input[@placeholder='* Фамилия']");
         By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
         By metroStationField = By.xpath("//input[@placeholder='* Станция метро']");
         By phoneField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
         By dateField = By.xpath("//input[@placeholder='* Когда привезти самокат']");
         By rentalPeriodField = By.xpath("//*[contains(text(), '* Срок аренды')]");
         By scooterBlackColorCheckbox = By.xpath("//label[contains(text(), 'чёрный жемчуг')]");
         By yesButton = By.xpath("//button[contains(text(), 'Да')]");
         By doneOrder = By.xpath("//*[contains(text(), 'Заказ оформлен')]");

         ChromeDriver driver = new ChromeDriver(new ChromeOptions());
         driver.get("https://qa-scooter.praktikum-services.ru/");

         new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(openFormButton));
         driver.findElement(openFormButton).click();
         new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(orderFormNextButton));

         driver.findElement(By.id("rcc-confirm-button")).click();

        driver.findElement(nameField).sendKeys("Джон");
        driver.findElement(surNameField).sendKeys("Джонсон");
        driver.findElement(addressField).sendKeys("ул. Плюшкина, д. Колотушкина");
        driver.findElement(metroStationField).click();
        driver.findElement(By.xpath("//*[contains(text(), 'Сокольники')]")).click();
        driver.findElement(phoneField).sendKeys("79999999999");
        driver.findElement(orderFormNextButton).click();

        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(), 'Про аренду')]")));

        driver.findElement(dateField).sendKeys("06.12.2022");
        driver.findElement(By.xpath("//*[contains(text(), 'Про аренду')]")).click(); //сбросим дейтпикер
        driver.findElement(rentalPeriodField).click();
        driver.findElement(By.xpath("//*[contains(text(), 'двое суток')]")).click();
        driver.findElement(scooterBlackColorCheckbox).click();

        driver.findElements(openFormButton).get(1).click();

        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(yesButton));

        driver.findElement(yesButton).click();
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'Заказ оформлен')]")).isDisplayed());

        driver.quit();
     }

}
