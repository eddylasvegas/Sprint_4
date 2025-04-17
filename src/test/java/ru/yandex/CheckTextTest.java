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
    // Вопросы о стоимости и оплате
    private static final String QUESTION_ABOUT_COST = "Сколько это стоит? И как оплатить?";
    private static final String ANSWER_ABOUT_COST = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";

    // Вопрос о нескольких самокатах
    private static final String QUESTION_ABOUT_MULTIPLE_SCOOTERS = "Хочу сразу несколько самокатов! Так можно?";
    private static final String ANSWER_ABOUT_MULTIPLE_SCOOTERS = "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";

    // Вопрос о расчете времени аренды
    private static final String QUESTION_ABOUT_RENT_TIME_CALCULATION = "Как рассчитывается время аренды?";
    private static final String ANSWER_ABOUT_RENT_TIME_CALCULATION = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";

    // Вопрос о срочном заказе
    private static final String QUESTION_ABOUT_TODAY_ORDER = "Можно ли заказать самокат прямо на сегодня?";
    private static final String ANSWER_ABOUT_TODAY_ORDER = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";

    // Вопрос о продлении заказа
    private static final String QUESTION_ABOUT_ORDER_EXTENSION = "Можно ли продлить заказ или вернуть самокат раньше?";
    private static final String ANSWER_ABOUT_ORDER_EXTENSION = "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.";

    // Вопрос о зарядке самоката
    private static final String QUESTION_ABOUT_CHARGING = "Вы привозите зарядку вместе с самокатом?";
    private static final String ANSWER_ABOUT_CHARGING = "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.";

    // Вопрос об отмене заказа
    private static final String QUESTION_ABOUT_ORDER_CANCELLATION = "Можно ли отменить заказ?";
    private static final String ANSWER_ABOUT_ORDER_CANCELLATION = "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.";

    // Вопрос о доставке за МКАД
    private static final String QUESTION_ABOUT_DELIVERY_OUTSIDE_MKAD = "Я жизу за МКАДом, привезёте?";
    private static final String ANSWER_ABOUT_DELIVERY_OUTSIDE_MKAD = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";

    public CheckTextTest(String question, String answer){
        this.question = question;
        this.answer = answer;
}

    @Parameterized.Parameters
    public static Object[][] getQuestions() {
        return new Object[][]{
                {QUESTION_ABOUT_COST, ANSWER_ABOUT_COST},
                {QUESTION_ABOUT_MULTIPLE_SCOOTERS, ANSWER_ABOUT_MULTIPLE_SCOOTERS},
                {QUESTION_ABOUT_RENT_TIME_CALCULATION, ANSWER_ABOUT_RENT_TIME_CALCULATION},
                {QUESTION_ABOUT_TODAY_ORDER, ANSWER_ABOUT_TODAY_ORDER},
                {QUESTION_ABOUT_ORDER_EXTENSION, ANSWER_ABOUT_ORDER_EXTENSION},
                {QUESTION_ABOUT_CHARGING, ANSWER_ABOUT_CHARGING},
                {QUESTION_ABOUT_ORDER_CANCELLATION, ANSWER_ABOUT_ORDER_CANCELLATION},
                {QUESTION_ABOUT_DELIVERY_OUTSIDE_MKAD, ANSWER_ABOUT_DELIVERY_OUTSIDE_MKAD}
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
