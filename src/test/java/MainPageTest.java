import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.hamcrest.CoreMatchers.is;

@RunWith(Parameterized.class)
public class MainPageTest {
    private final int targetItemNumber;
    private final String expectedAnswerText;
    private final WebDriver webDriver;
    private final MainPage mainPage;

    public MainPageTest(int itemNumber, String answerText) {
        this.webDriver = new ChromeDriver(new ChromeOptions());
        this.mainPage = new MainPage(this.webDriver);

        this.targetItemNumber = itemNumber;
        this.expectedAnswerText = answerText;
    }

    @Parameterized.Parameters
    public static Object[][] getAnswers() {
        return new Object[][]{
                {1, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {2, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {3, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {4, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {5, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {6, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {7, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {8, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Test
    public void question() {
        this.mainPage.openPage();
        this.mainPage.clickAcceptCookiesButton();
        this.mainPage.waitForAccordionHeadingClickable();

        this.mainPage.scrollToAccordionItem(this.targetItemNumber);

        String answerText = this.mainPage.getAccordionItemAnswerText(this.targetItemNumber);
        MatcherAssert.assertThat(answerText, is(this.expectedAnswerText));

        this.webDriver.quit();
    }
}


