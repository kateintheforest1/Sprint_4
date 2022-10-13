import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MainPage {
    private final WebDriver webDriver;
    private final By acceptCookiesButton = By.id("rcc-confirm-button");
    private final By accordionHeading = By.className("accordion__heading");

    private final By orderFormButton = By.xpath("//button[contains(text(), 'Заказать')]");

    public MainPage(WebDriver driver) {
        this.webDriver = driver;
    }

    private By getAccordionItemButton(int index) {
        return By.id("accordion__heading-" + index);
    }

    private By getAccordionItemAnswer(int index) {
        return By.xpath("//*[@id=\"accordion__panel-" + index + "\"]/p");
    }

    public void openPage() {
        this.webDriver.get("https://qa-scooter.praktikum-services.ru/");
    }

    public void clickAcceptCookiesButton() {
        this.webDriver.findElement(acceptCookiesButton).click();
    }

    public void scrollToAccordionItem(int itemNumber) {
        Actions actions = new Actions(this.webDriver);
        By accordionItemButton = this.getAccordionItemButton(itemNumber - 1);

        actions.moveToElement(this.webDriver.findElement(accordionItemButton));
        actions.perform();
    }

    public String getAccordionItemAnswerText(int itemNumber) {
        int itemIndex = itemNumber - 1;

        By accordionItemButton = this.getAccordionItemButton(itemIndex);
        By accordionItemAnswer = this.getAccordionItemAnswer(itemIndex);

        this.webDriver.findElement(accordionItemButton).click();

        return this.webDriver.findElement(accordionItemAnswer).getText();
    }

    public void waitForAccordionHeadingClickable() {
        new WebDriverWait(this.webDriver, 3).until(
                ExpectedConditions.elementToBeClickable(this.accordionHeading)
        );
    }

    public void waitForOpenOrderFormButtonIsClickable() {
        new WebDriverWait(this.webDriver, 3).until(
                ExpectedConditions.elementToBeClickable(this.orderFormButton)
        );
    }

    public void openOrderForm() {
        this.webDriver.findElement(this.orderFormButton).click();
    }
}


