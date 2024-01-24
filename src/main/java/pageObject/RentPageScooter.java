package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RentPageScooter {
    private final WebDriver driver;
    private By dateDeliveryField = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[1]/div/div/input");
    private By blackColorScooter= By.id("black");
    private By commentField = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[4]/input");
    private By orderResultButton = By.xpath("//*[@id='root']/div/div[2]/div[3]/button[2]");
    private By periodDateField = By.className("Dropdown-control");
    private By modalWindowButton = By.xpath(".//button[text() = 'Да']");

    public RentPageScooter(WebDriver driver) {
        this.driver = driver;
    }

    public void fillRentField(String dateRent, String comment) {
        driver.findElement(dateDeliveryField).sendKeys(dateRent);
        driver.findElement(blackColorScooter).click();
        driver.findElement(commentField).sendKeys(comment);
    }

    public void fillAutoCompleteRentField(String periodRent) {
        final String periodRentTemplate = ".//div[@class='Dropdown-menu']/div[text()='" + periodRent + "']";
        driver.findElement(periodDateField).click();
        driver.findElement(By.xpath(periodRentTemplate)).click();
    }

    public void orderButton() {
        driver.findElement(orderResultButton).click();
    }

    public void displayedConfirmationModalWindow() {
        final By textAboutImportantField = By.xpath(".//div[@class='Order_Modal__YZ-d3']/div[text()='Хотите оформить заказ?']");
        driver.findElement(textAboutImportantField).isDisplayed();
    }

    public void modalWindowButton() {
        driver.findElement(modalWindowButton).click();
    }

    public void displayedIssuedModalWindow() {
        final By textAboutImportantField = By.xpath(".//div[@class='Order_Modal__YZ-d3']/div[text()='Заказ оформлен']");
        driver.findElement(textAboutImportantField).isDisplayed();
    }
}

