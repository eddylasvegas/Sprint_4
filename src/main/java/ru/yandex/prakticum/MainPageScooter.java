package ru.yandex.prakticum;

import org.openqa.selenium.*;

public class MainPageScooter {

    private WebDriver driver;


    //Кнопка подтверждения куки локатор
    private static final By cookieButton = By.id("rcc-confirm-button");

    //локатор блока Вопросы о важном
    private final By blockQuestions = By.className("Home_FourPart__1uthg");


    public MainPageScooter(WebDriver driver){
        this.driver = driver;
    }

    //метод для клика по кнопке подтверждения куки
    public void clickButtonCookie() {
        driver.findElement(cookieButton).click();
    }

    //метод со скроллом до блока с вопросами
    public void scrollToQuestions(){
        //находим элемент по классу в блоке Вопросы о важном
        WebElement listQuestions = driver.findElement(blockQuestions);
        //прокручиваем страницу до этого элемента
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", listQuestions);
    }
    //метод для клика по вопросу из блока по заданному тексту
    public void clickOnQuestionByText(String text) {
        driver.findElement(By.xpath(".//div[text() = '" + text + "']")).click();
    }

    //метод для получегия текста из открытоко вопроса из блока с вопросами
    public String getAnswerText(String question) {
        // Находим ответ, который следует за кликнутым вопросом
        WebElement answerElement = driver.findElement(By.xpath(
                ".//div[text()='" + question + "']/../..//div[@class='accordion__panel']/p"));
        return answerElement.getText();
    }
}