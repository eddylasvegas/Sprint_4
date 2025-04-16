package ru.yandex.prakticum;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPageScooter {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public OrderPageScooter(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    //Поле ввода имени
    private static final By nameField = By.xpath("//input[@placeholder='* Имя']");

    //Поле ввода фамилии
    private static final By lastNameField = By.xpath("//input[@placeholder='* Фамилия']");

    //Поле ввода адреса
    private static final By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");

    //Поле ввода станции метро
    private static final By metroFieldClick = By.className("select-search");
    private static final By metroField = By.xpath(".//input[@placeholder='* Станция метро']");
    private static final By cherkizovskayaStation = By.xpath("//div[text()='Черкизовская']");

    //Поле ввода телефона
    private static final By phoneField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");

    //кнопка Далее на странице формы1
    private static final By nextButton = By.xpath(".//button[text()='Далее']");

    //Поле ввода даты заказа
    private static final By dateOrderField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private static final By date30April = By.xpath("//div[@aria-label='Choose среда, 30-е апреля 2025 г.']");

    //ПОле ввода срока аренды
    private static final By rentalField = By.xpath("//div[text()='* Срок аренды']");

    //Опция выпадающего списка срока аренды "трое суток"
    private static final By threeDayOption = By.xpath("//div[@class='Dropdown-option' and text()='трое суток']");

    //Цвет самоката черный
    private static final By blackColorScooter = By.id("black");

    //Цвет самоката серый
    private static final By greyColorScooter = By.id("grey");

    //Поле с комментарием для курьера
    private static final By commentField = By.xpath("//input[@placeholder='Комментарий для курьера']");

    //Кнопка заказать под формой заказа на странице 2
    private static final By finishOrderButton = By.xpath("//button[text()='Заказать' and @class='Button_Button__ra12g Button_Middle__1CSJM']");

    //Кнопка "Да" подтверждения заказа
    private static final By buttonYes = By.xpath("//button[text()='Да']");

    //Окно оформленного заказа
    private static final By orderCompletedBlock = By.xpath("//div[contains(@class, 'Order_Modal__YZ-d3')]");
    //Текст в окне оформленного заказа
    private static final By orderCompletedText = By.xpath(".//div[contains(@class, 'Order_ModalHeader__3FDaJ')]");



    //заполняем поле имя
    public void enterNameField(String text){
        driver.findElement(nameField).sendKeys(text);
    }
    //заполняем поле фамилия
    public void enterLastNameField(String text){
        driver.findElement(lastNameField).sendKeys(text);
    }
    //заполняем поле адрес
    public void enterAddressField(String text){
        driver.findElement(addressField).sendKeys(text);
    }

    //выбор станции метро
    public void clickMetroField(){
        driver.findElement(metroFieldClick).click();
    }
    public void enterMetroStation(String text){
        driver.findElement(metroField).sendKeys(text);
    }
    public void clickMetroStation(){
        driver.findElement(cherkizovskayaStation).click();
    }

    //заполняем поле мобильного телефона
    public void enterPhoneField(String text){
        driver.findElement(phoneField).sendKeys(text);
    }

    //Клик по кнопке далее
    public void clickButtonNext(){
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
    }

    //Объединение методов заполнения 1 страницы формы
    public void fillOrderFormPageOne(String firstName, String lastName, String address, String metroStation, String phoneNumber){
        enterNameField(firstName);
        enterLastNameField(lastName);
        enterAddressField(address);
        clickMetroField();
        enterMetroStation(metroStation);
        clickMetroStation();
        enterPhoneField(phoneNumber);
        clickButtonNext();
    }

    //заполняем поле ввода даты заказа
    public void enterDataOrderField(String text){
        driver.findElement(dateOrderField).sendKeys(text);
    }
    public void clickDeliveryDate(){
        driver.findElement(date30April).click();
    }

    //клик и выбор опцию поля срок аренды
    public void clickRentalField(){
        driver.findElement(rentalField).click();
    }
    public void enterRentalOption(){
        driver.findElement(threeDayOption).click();
    }

    //выбор опции цвета самоката
    public void enterColourOption(){
        driver.findElement(blackColorScooter).click();
    }
    //заполняем поле комментарий
    public void enterComment(String text){
        driver.findElement(commentField).sendKeys(text);
    }

    //нажимаем нижнюю кнопку заказать на 2 странице формы
    public void clickFinishButton(){
        wait.until(ExpectedConditions.elementToBeClickable(finishOrderButton)).click();
    }

    //Объединение методов заполнения 2 страницы формы
    public void fillOrderFormPageTwo(String deliveryDate, String comment){
        enterDataOrderField(deliveryDate);
        clickDeliveryDate();
        clickRentalField();
        enterRentalOption();
        enterColourOption();
        enterComment(comment);
        clickFinishButton();
    }


    //подтверждаем оформление заказа клкнув на кнопку Да
    public void clickButtonYes() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonYes)).click();
    }

    //Метод для проверки появления окна с оформленным заказом
    public boolean checkOrderCompleted() {
        try {
            WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(orderCompletedBlock));
            return modal.findElement(orderCompletedText).getText().contains("Заказ оформлен");
        } catch (TimeoutException e) {
            return false;
        }
    }
}
