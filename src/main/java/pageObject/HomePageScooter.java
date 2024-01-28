package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class HomePageScooter {
    private final WebDriver driver;
    private final By orderInFooterButton = By.className("Button_Button__ra12g");
    private final By answers = By.xpath(".//div[@class='accordion__panel']//*");
    private final By textAboutImportantField = By.xpath(".//div[@class='accordion__panel']//*");
    private final By headerAboutImportantField = By.className("Home_FourPart__1uthg");
    public final By orderInBodyButton = By.xpath("//div[@class='Home_FinishButton__1_cWm']/button");

    public HomePageScooter(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadAccordionData() {
        new WebDriverWait(driver,  Duration.ofSeconds(30)).until(driver -> (driver.findElement(textAboutImportantField).getText() != null
        ));
    }

    public String checkText(String textAboutImportantQuestion, int counter) {
        String textAnswers = null;
        final String questionOptionTemplate = ".//div[@class='accordion']//*[text()='" + textAboutImportantQuestion + "']";
        driver.findElement(By.xpath(questionOptionTemplate)).click();
        List<WebElement> answersList = driver.findElements(answers);
        waitForLoadAccordionData();
        for (int i = 0; i <= answersList.size(); i++) {
            textAnswers = answersList.get(counter).getText();
        }
        return textAnswers;
    }

    public void waitForLoadProfileData() {
        new WebDriverWait(driver,  Duration.ofSeconds(30)).until(driver -> (driver.findElement(headerAboutImportantField).getText() != null
                && !driver.findElement(headerAboutImportantField).getText().isEmpty()
        ));
    }

    public void clickOrderFromFooterButton() {
        driver.findElement(orderInFooterButton).click();
    }

    public void clickOrderFromBodyButton() {
        driver.findElement(orderInBodyButton).click();
    }
}

