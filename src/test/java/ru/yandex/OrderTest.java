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
import ru.yandex.prakticum.OrderPageScooter;

import static org.junit.Assert.assertTrue;


@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;

    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phoneNumber;
    private final String deliveryDate;
    private final String comment;

    public OrderTest(String firstName, String lastName, String address,  String metroStation, String phoneNumber, String deliveryDate, String comment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.deliveryDate = deliveryDate;
        this.comment = comment;

    }

    @Parameterized.Parameters
    public static Object[][] enterForm() {
        return new Object[][]{
                {"Эльдар","Гамидов","улица Ленина 1", "Черкизовская", "+79112596666", "30/04/2025", "Без опозданий, пожалуйста." },
                {"Иван","Иванов","улица Ленина,51", "Черкизовская", "+71231231212", "01.05.2025", "Не опаздывать!" },
        };
    }
    @Before
    public void StartUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        //WebDriverManager.firefoxdriver().setup();
        //driver = new FirefoxDriver();

        driver.get("https://qa-scooter.praktikum-services.ru/");
        OrderPageScooter orderPageScooter = new OrderPageScooter(driver);
        orderPageScooter.clickButtonCookie();

    }

    @Test
    public void orderButtonOneTest() throws InterruptedException {
        OrderPageScooter orderPageScooter = new OrderPageScooter(driver);
        orderPageScooter.clickButtonOrderOne();
        orderPageScooter.enterNameField(firstName);
        orderPageScooter.enterLastNameField(lastName);
        orderPageScooter.enterAddressField(address);
        orderPageScooter.clickMetroField();
        orderPageScooter.enterMetroStation(metroStation);
        orderPageScooter.clickMetroStation();
        orderPageScooter.enterPhoneField(phoneNumber);
        orderPageScooter.clickButtonNext();
        orderPageScooter.enterDataOrderField(deliveryDate);
        orderPageScooter.clickDeliveryDate();
        orderPageScooter.clickRentalField();
        orderPageScooter.enterRentalOption();
        orderPageScooter.enterColourOption();
        orderPageScooter.enterComment(comment);
        orderPageScooter.clickFinishButton();
        orderPageScooter.clickButtonYes();
        Thread.sleep(2000);  // Пауза 2 секунды
        assertTrue(orderPageScooter.checkOrderCompleted());
    }

    @Test
    public void orderButtonTwoTest() throws InterruptedException {
        OrderPageScooter orderPageScooter = new OrderPageScooter(driver);
        orderPageScooter.clickButtonOrderTwo();
        orderPageScooter.enterNameField(firstName);
        orderPageScooter.enterLastNameField(lastName);
        orderPageScooter.enterAddressField(address);
        orderPageScooter.clickMetroField();
        orderPageScooter.enterMetroStation(metroStation);
        orderPageScooter.clickMetroStation();
        orderPageScooter.enterPhoneField(phoneNumber);
        orderPageScooter.clickButtonNext();
        orderPageScooter.enterDataOrderField(deliveryDate);
        orderPageScooter.clickDeliveryDate();
        orderPageScooter.clickRentalField();
        orderPageScooter.enterRentalOption();
        orderPageScooter.enterColourOption();
        orderPageScooter.enterComment(comment);
        orderPageScooter.clickFinishButton();
        orderPageScooter.clickButtonYes();
        Thread.sleep(2000);  // Пауза 2 секунды
        assertTrue(orderPageScooter.checkOrderCompleted());
    }



    @After
    public void tearDown(){
        driver.quit();
    }
}
