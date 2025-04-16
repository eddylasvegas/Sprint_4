package ru.yandex;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.prakticum.MainPageScooter;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CheckTextTest {
    private static WebDriver driver;
    private final String question;
    private final String answer;

    // Константы с тестовыми данными
    private static final String QUESTION_ONE = "Сколько это стоит? И как оплатить?";
    private static final String ANSWER_ONE = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";

    private static final String QUESTION_TWO = "Хочу сразу несколько самокатов! Так можно?";
    private static final String ANSWER_TWO = "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";

    private static final String QUESTION_THREE = "Как рассчитывается время аренды?";
    private static final String ANSWER_THREE = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";

    private static final String QUESTION_FOUR = "Можно ли заказать самокат прямо на сегодня?";
    private static final String ANSWER_FOUR = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";

    private static final String QUESTION_FIVE = "Можно ли продлить заказ или вернуть самокат раньше?";
    private static final String ANSWER_FIVE = "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.";

    private static final String QUESTION_SIX = "Вы привозите зарядку вместе с самокатом?";
    private static final String ANSWER_SIX = "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.";

    private static final String QUESTION_SEVEN = "Можно ли отменить заказ?";
    private static final String ANSWER_SEVEN = "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.";

    private static final String QUESTION_EIGHT = "Я жизу за МКАДом, привезёте?";
    private static final String ANSWER_EIGHT = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";

    public CheckTextTest(String question, String answer){
        this.question = question;
        this.answer = answer;
}

    @Parameterized.Parameters
    public static Object[][] getQuestions() {
        return new Object[][]{
                {QUESTION_ONE, ANSWER_ONE},
                {QUESTION_TWO, ANSWER_TWO},
                {QUESTION_THREE, ANSWER_THREE},
                {QUESTION_FOUR, ANSWER_FOUR},
                {QUESTION_FIVE, ANSWER_FIVE},
                {QUESTION_SIX, ANSWER_SIX},
                {QUESTION_SEVEN, ANSWER_SEVEN},
                {QUESTION_EIGHT, ANSWER_EIGHT}
        };
    }



    @Before
    public void StartUp() throws InterruptedException{
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //WebDriverManager.firefoxdriver().setup();
        //driver = new FirefoxDriver();


        // Установка неявного ожидания
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // Разворачиваем окно браузера на весь экран
        driver.manage().window().maximize();

        driver.get(MainPageScooter.URL); // Используем константу URL из MainPageScooter
        MainPageScooter mainPageScooter = new MainPageScooter(driver);
        mainPageScooter.clickButtonCookie();
        mainPageScooter.scrollToQuestions();

    }

    @Test
    public void clickQuestionsTest()  {
        MainPageScooter mainPageScooter = new MainPageScooter(driver);
        mainPageScooter.clickOnQuestionByText(question);
        String actualText = mainPageScooter.getAnswerText(question);
        assertEquals(answer, actualText);
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
