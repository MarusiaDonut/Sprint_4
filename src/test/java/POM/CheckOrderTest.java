package POM;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;

public class CheckOrderTest {

    private WebDriver driver;
    final private String firstName = "Иван";
    final private String lastName = "Иванов";
    final private String address = "улица Мира, 95";
    final private String number = "89758576758";
    final  private String metroStation = "Смоленская";
    final  private String dateRent = "30.01.2024";
    final  private String comment = "Тест для курьера";
    final  private String periodRent = "сутки";


    @Before
    public void startTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.id("rcc-confirm-button")).click();
    }

    @Test
    public void shouldCheckOrderInFooterButtonTest() {
        HomePageScooter homePageScooter = new HomePageScooter(driver);
        homePageScooter.clickOrderFromFooterButton();

        OrderPageScooter orderPageScooter = new OrderPageScooter(driver);
        orderPageScooter.fillAutoCompleteOrderField(metroStation);
        orderPageScooter.fillOrderField(firstName, lastName, address, number);
        orderPageScooter.furtherClick();

        RentPageScooter rentPageScooter = new RentPageScooter(driver);
        rentPageScooter.fillRentField(dateRent, comment);
        rentPageScooter.fillAutoCompleteRentField(periodRent);
        rentPageScooter.orderButton();
        rentPageScooter.displayedConfirmationModalWindow();
        rentPageScooter.modalWindowButton();
        rentPageScooter.displayedIssuedModalWindow();
    }

    @Test
    public void shouldCheckOrderInBodyButtonTest() {
        HomePageScooter homePageScooter = new HomePageScooter(driver);
        homePageScooter.clickOrderFromBodyButton();

        OrderPageScooter orderPageScooter = new OrderPageScooter(driver);
        orderPageScooter.fillAutoCompleteOrderField(metroStation);
        orderPageScooter.fillOrderField(firstName, lastName, address, number);
        orderPageScooter.furtherClick();

        RentPageScooter rentPageScooter = new RentPageScooter(driver);
        rentPageScooter.fillRentField(dateRent, comment);
        rentPageScooter.fillAutoCompleteRentField(periodRent);
        rentPageScooter.orderButton();
        rentPageScooter.displayedConfirmationModalWindow();
        rentPageScooter.modalWindowButton();
        rentPageScooter.displayedIssuedModalWindow();
    }

    @After
    public void teardown() {
        driver.quit();
    }
}

