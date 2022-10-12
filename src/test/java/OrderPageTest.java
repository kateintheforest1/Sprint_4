import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class OrderPageTest {

   private final WebDriver webDriver;
   private final OrderPage orderPage;

   public OrderPageTest() {
      this.webDriver = new ChromeDriver(new ChromeOptions());
      this.orderPage = new OrderPage(this.webDriver);
   }

     @Test
   public void testOrder() {
     MainPage mainPage = new MainPage(this.webDriver);

     mainPage.openPage();
     mainPage.clickAcceptCookiesButton();
     mainPage.waitForOpenOrderFormButtonIsClickable();

     mainPage.openOrderForm();
     this.orderPage.waitForNextFormPageButtonIsClickable();

     this.orderPage.setName("Джон");
     this.orderPage.setSurname("Джонсон");
     this.orderPage.setAddress("ул. Плюшкина, д. Колотушкина");
     this.orderPage.selectMetroStation("Сокольники");
     this.orderPage.setPhoneNumber("79999999999");

     this.orderPage.openNextFormPage();
     this.orderPage.waitFormSecondPageIsClickable();

     this.orderPage.setOrderDate("06.12.2022");
     this.orderPage.selectRentalPeriod("двое суток");
     this.orderPage.selectBlackColorScooter();

     this.orderPage.clickSendOrderForm();
     this.orderPage.awaitConfirmSendOrderButtonIsClickable();

     this.orderPage.clickConfirmSendOrderButton();

     boolean orderIsProcessed = this.orderPage.orderIsProcessed();

     Assert.assertTrue(orderIsProcessed);

     this.webDriver.quit();
   }
}
