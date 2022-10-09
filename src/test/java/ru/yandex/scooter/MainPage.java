package ru.yandex.scooter;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.is;

@RunWith(Parameterized.class)
public class MainPage {
    private By arrowLocator;
    private By answerLocator;
    private String answerText;
    private By cookieButton = By.id("rcc-confirm-button");

    public MainPage(By arrowLocator, By answerLocator, String answerText) {
        this.arrowLocator = arrowLocator;
        this.answerLocator = answerLocator;
        this.answerText = answerText;
    }

    @Parameterized.Parameters
    public static Object[][] getAnswers() {
        By firstArrow = By.className("accordion__heading");
        By firstAnswer = By.xpath("//*[@id=\"accordion__panel-0\"]/p");
        By secondArrow = By.id("accordion__heading-1");
        By secondAnswer = By.xpath("//*[@id=\"accordion__panel-1\"]/p");
        By thirdArrow = By.id("accordion__heading-2");
        By thirdAnswer = By.xpath("//*[@id=\"accordion__panel-2\"]/p");
        By fourthArrow = By.id("accordion__heading-3");
        By fourthAnswer = By.xpath("//*[@id=\"accordion__panel-3\"]/p");
        By fifthArrow = By.id("accordion__heading-4");
        By fifthAnswer = By.xpath("//*[@id=\"accordion__panel-4\"]/p");
        By sixthArrow = By.id("accordion__heading-5");
        By sixthAnswer = By.xpath("//*[@id=\"accordion__panel-5\"]/p");
        By seventhArrow = By.id("accordion__heading-6");
        By seventhAnswer = By.xpath("//*[@id=\"accordion__panel-6\"]/p");
        By eightArrow = By.id("accordion__heading-7");
        By eightAnswer = By.xpath("//*[@id=\"accordion__panel-7\"]/p");

        return new Object[][]{
                {firstArrow, firstAnswer, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {secondArrow, secondAnswer, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {thirdArrow, thirdAnswer, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {fourthArrow, fourthAnswer, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {fifthArrow, fifthAnswer, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {sixthArrow, sixthAnswer, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {seventhArrow, seventhAnswer, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {eightArrow, eightAnswer, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Test
    public void Question() {
        ChromeOptions options = new ChromeOptions();

        ChromeDriver driver = new ChromeDriver(options);

//        FirefoxOptions options  = new FirefoxOptions();

//        FirefoxDriver driver = new FirefoxDriver();

        driver.get("https://qa-scooter.praktikum-services.ru/");

        driver.findElement(cookieButton).click();

        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(By.className("accordion__heading")));

        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(this.arrowLocator));
        actions.perform();

        driver.findElement(arrowLocator).click();
        String text = driver.findElement(answerLocator).getText();
        MatcherAssert.assertThat(text, is(answerText));

        driver.quit();
    }
}


