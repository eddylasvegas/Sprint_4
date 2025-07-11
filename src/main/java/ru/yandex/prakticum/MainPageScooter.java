package ru.yandex.prakticum;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPageScooter {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Константа с URL
    public static final String URL = "https://qa-scooter.praktikum-services.ru/";

    //Кнопка подтверждения куки локатор
    private static final By cookieButton = By.id("rcc-confirm-button");

    //Кнопка Заказать сверху
    private static final By orderButtonTop = By.className("Button_Button__ra12g");

    //Кнопка заказать снизу
    private static final By orderButtonBottom = By.xpath("(//button[contains(@class, 'Button_Button__ra12g') and contains(text(), 'Заказать')])[2]");


    //локатор блока Вопросы о важном
    private final By blockQuestions = By.className("Home_FourPart__1uthg");

    //локатор текста ответа
    private final By answerText = By.xpath(".//div[@class='accordion__panel']/p");


    public MainPageScooter(WebDriver driver){

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //метод для клика по кнопке подтверждения куки
    public void clickButtonCookie() {
        wait.until(ExpectedConditions.elementToBeClickable(cookieButton)).click();
    }

    //Открываем страницу заказа через кнопку Заказать1
    public void clickButtonOrderTop(){

        wait.until(ExpectedConditions.elementToBeClickable(orderButtonTop)).click();
    }

    //Открываем страницу заказа через кнопку Заказать2
    public void clickButtonOrderBottom(){

        wait.until(ExpectedConditions.elementToBeClickable(orderButtonBottom)).click();
    }

    //метод со скроллом до блока с вопросами
    public void scrollToQuestions(){
        //находим элемент по классу в блоке Вопросы о важном
        WebElement listQuestions = wait.until(ExpectedConditions.visibilityOfElementLocated(blockQuestions));
        //прокручиваем страницу до этого элемента
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", listQuestions);
    }
    //метод для клика по вопросу из блока по заданному тексту
    public void clickOnQuestionByText(String text) {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(".//div[text() = '" + text + "']"))).click();
    }

    //метод для получения текста из открытого вопроса из блока с вопросами
    public String getAnswerText(String question) {
        // Находим ответ, который следует за кликнутым вопросом
        WebElement answerElement = wait.until(ExpectedConditions.visibilityOf(
                driver.findElement(By.xpath(".//div[text()='" + question + "']/../.."))
                        .findElement(answerText)));
        return answerElement.getText();
    }
}