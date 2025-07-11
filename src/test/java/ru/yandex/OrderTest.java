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

import java.time.Duration;

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


        // Установка неявного ожидания
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // Разворачиваем окно браузера на весь экран
        driver.manage().window().maximize();

        driver.get(MainPageScooter.URL); // Используем константу URL из MainPageScooter
        OrderPageScooter orderPageScooter = new OrderPageScooter(driver);
        MainPageScooter mainPageScooter = new MainPageScooter(driver);
        mainPageScooter.clickButtonCookie();

    }

    @Test
    public void orderButtonTopTest() throws InterruptedException {
        OrderPageScooter orderPageScooter = new OrderPageScooter(driver);
        MainPageScooter mainPageScooter = new MainPageScooter(driver);
        mainPageScooter.clickButtonOrderTop();

        //выполняем заполнение формы первой страницы
        orderPageScooter.fillOrderFormPageOne(firstName, lastName, address, metroStation, phoneNumber);
        //выполняем заполнение формы второй страницы
        orderPageScooter.fillOrderFormPageTwo(deliveryDate, comment);

        orderPageScooter.clickButtonYes();

        assertTrue(orderPageScooter.checkOrderCompleted());
    }

    @Test
    public void orderButtonBottomTest() throws InterruptedException {
        OrderPageScooter orderPageScooter = new OrderPageScooter(driver);
        MainPageScooter mainPageScooter = new MainPageScooter(driver);
        mainPageScooter.clickButtonOrderBottom();

        //выполняем заполнение формы первой страницы
        orderPageScooter.fillOrderFormPageOne(firstName, lastName, address, metroStation, phoneNumber);
        //выполняем заполнение формы второй страницы
        orderPageScooter.fillOrderFormPageTwo(deliveryDate, comment);

        orderPageScooter.clickButtonYes();

        assertTrue(orderPageScooter.checkOrderCompleted());
    }



    @After
    public void tearDown(){
        driver.quit();
    }
}
