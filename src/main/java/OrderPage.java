
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {

    private final WebDriver webDriver;
    private final By nextFormPageButton = By.xpath("//button[contains(text(), 'Далее')]");
    private final By nameField = By.xpath("//input[@placeholder='* Имя']");
    private final By surnameField = By.xpath("//input[@placeholder='* Фамилия']");
    private final By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroStationField = By.xpath("//input[@placeholder='* Станция метро']");
    private final By phoneField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By orderDateField = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriodField = By.xpath("//*[contains(text(), '* Срок аренды')]");
    private final By scooterBlackColorCheckbox = By.xpath("//label[contains(text(), 'чёрный жемчуг')]");
    private final By sendOrderFormButton = By.xpath("//button[contains(text(), 'Заказать')]");
    private final By confirmSendOrderButton = By.xpath("//button[contains(text(), 'Да')]");

    public OrderPage(WebDriver driver) {
        this.webDriver = driver;
    }

    public void waitForNextFormPageButtonIsClickable() {
        new WebDriverWait(this.webDriver, 3).until(
                ExpectedConditions.elementToBeClickable(this.nextFormPageButton)
        );
    }

    public void waitFormSecondPageIsClickable() {
        new WebDriverWait(this.webDriver, 3).until(
                ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(), 'Про аренду')]"))
        );
    }

    public void setName(String name) {
        this.webDriver.findElement(nameField).sendKeys(name);
    }

    public void setSurname(String surname) {
        this.webDriver.findElement(surnameField).sendKeys(surname);
    }

    public void setAddress(String address) {
        this.webDriver.findElement(addressField).sendKeys(address);
    }

    public void selectMetroStation(String stationName) {
        this.webDriver.findElement(metroStationField).click();
        this.webDriver.findElement(By.xpath("//*[contains(text(), '" + stationName + "')]")).click();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.webDriver.findElement(phoneField).sendKeys(phoneNumber);
    }

    public void openNextFormPage() {
        this.webDriver.findElement(nextFormPageButton).click();
    }

    public void setOrderDate(String orderDate) {
        this.webDriver.findElement(orderDateField).sendKeys(orderDate);

        // close datePicker
        this.webDriver.findElement(By.xpath("//*[contains(text(), 'Про аренду')]")).click();
    }

    public void selectRentalPeriod(String rentalPeriod) {
        this.webDriver.findElement(rentalPeriodField).click();
        this.webDriver.findElement(By.xpath("//*[contains(text(), '" + rentalPeriod + "')]")).click();
    }

    public void selectBlackColorScooter() {
        this.webDriver.findElement(scooterBlackColorCheckbox).click();
    }

    public void clickSendOrderForm() {
        this.webDriver.findElements(this.sendOrderFormButton).get(1).click();
    }

    public void clickConfirmSendOrderButton() {
        this.webDriver.findElements(this.confirmSendOrderButton).get(1).click();
    }

    public void awaitConfirmSendOrderButtonIsClickable() {
        new WebDriverWait(this.webDriver, 3).until(
                ExpectedConditions.elementToBeClickable(this.confirmSendOrderButton)
        );
    }

    public boolean orderIsProcessed() {
        return this.webDriver.findElement(By.xpath("//*[contains(text(), 'Заказ оформлен')]")).isDisplayed();
    }

}
