package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class OrderPageScooter {
    private final WebDriver driver;
    private By FirstNameField = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[1]/input");
    private By LastNameField = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[2]/input");
    private By AddressField = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[3]/input");
    private By MetroStationField = By.className("select-search__input");
    private By NumberField = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[5]/input");

    public OrderPageScooter(WebDriver driver) {
        this.driver = driver;
    }

    public void fillOrderField(String firstName, String lastName, String address, String number) {
        driver.findElement(FirstNameField).sendKeys(firstName);
        driver.findElement(LastNameField).sendKeys(lastName);
        driver.findElement(AddressField).sendKeys(address);
        driver.findElement(NumberField).sendKeys(number);
    }

    public void fillAutoCompleteOrderField(String metroStation) {
        final String metroOptionTemplate = ".//div[@class='select-search__select']//*[text()='" + metroStation + "']";
        driver.findElement(MetroStationField).sendKeys(metroStation);
        driver.findElement(By.xpath(metroOptionTemplate)).click();
    }

    public void furtherClick() {
        final String furtherButtonTemplate = ".//div[@class='Order_NextButton__1_rCA']//*[text()='Далее']";
        driver.findElement(By.xpath(furtherButtonTemplate)).click();
    }
}

