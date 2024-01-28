package scooter;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObject.HomePageScooter;
import pageObject.OrderPageScooter;
import pageObject.RentPageScooter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class CheckOrderTest {
    WebDriver driver;
    int numberOrderButton;
    private final String firstName;
    final private String lastName;
    final private String address;
    final private String number;
    final  private String metroStation;
    final  private String dateRent;
    final  private String comment;
    final  private String periodRent;
    final private By cookieButton = By.id("rcc-confirm-button");

    public CheckOrderTest(int numberOrderButton, String firstName, String lastName, String address, String number,
                            String metroStation, String dateRent, String comment,String periodRent ) {
        this.numberOrderButton = numberOrderButton;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.number = number;
        this.metroStation = metroStation;
        this.dateRent = dateRent;
        this.comment = comment;
        this.periodRent = periodRent;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {1, "Иван", "Иванов", "улица Мира, 95", "89758576758", "Смоленская", "30.01.2024", "Тест для курьера", "сутки"},
                {2, "Екатерина", "Петрова", "улица Дружбы, 15", "89564756475", "Смоленская", "03.02.2024", "Тест для курьера №2", "сутки"},
        };
    }

    @Before
    public void startTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(cookieButton).click();

    }

    @Test
    public void shouldCheckOrderInFooterButtonTest() {
        HomePageScooter homePageScooter = new HomePageScooter(driver);
        if (numberOrderButton == 1) {
            homePageScooter.clickOrderFromFooterButton();
        } else {
            homePageScooter.clickOrderFromBodyButton();
        }

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

