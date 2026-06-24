package pageObjects;

import org.openqa.selenium.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class HomePage {

    WebDriver driver;
    WebDriverWait wait;

    // ✅ Logger added
    private static final Logger logger = LogManager.getLogger(HomePage.class);

    public HomePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    private By fromField = By.xpath("//input[@placeholder='From City']");
    private By toField = By.xpath("//input[@placeholder='To City']");
    private By activeFromInput = By.xpath("(//input[@type='text'])[1]");
    private By activeToInput = By.xpath("(//input[@type='text'])[2]");
    private By searchBtn = By.xpath("//button[contains(.,'Search')]");

    private WebElement waitAndClick(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        return element;
    }

    private WebElement waitAndSendKeys(By locator, String value) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(value);
        return element;
    }

    private void scrollToElement(By locator) {

        WebElement element = wait.until(
                ExpectedConditions.presenceOfElementLocated(locator));

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
            "arguments[0].scrollIntoView({block: 'center'});", element
        );

        js.executeScript("window.scrollBy(0, -100);");

        logger.info("Scrolled to element: " + locator.toString());
    }

    public void enterFromCity(String city){

        logger.info("Entering FROM city: " + city);

        waitAndClick(fromField);

        waitAndSendKeys(activeFromInput, city);

        try { Thread.sleep(1000); } catch (Exception e) {}

        By cityOption = By.xpath("//div[contains(@class,'search') or contains(@class,'auto')]//div[contains(normalize-space(.),'" + city + "')]");

        wait.until(ExpectedConditions.visibilityOfElementLocated(cityOption));

        scrollToElement(cityOption);

        waitAndClick(cityOption);

        logger.info("FROM city selected successfully: " + city);
    }

    public void enterToCity(String city) {

        logger.info("Entering TO city: " + city);

        waitAndClick(toField);

        waitAndSendKeys(activeToInput, city);

        try { Thread.sleep(1000); } catch (Exception e) {}

        By cityOption = By.xpath("//div[contains(@class,'search') or contains(@class,'auto')]//div[contains(normalize-space(.),'" + city + "')]");

        wait.until(ExpectedConditions.visibilityOfElementLocated(cityOption));

        scrollToElement(cityOption);

        waitAndClick(cityOption);

        logger.info("TO city selected successfully: " + city);
    }

    public void clickSearch() {

        logger.info("Clicking on Search button");

        waitAndClick(searchBtn);

        try { Thread.sleep(3000); } catch (Exception e) {}

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(
            driver -> ((JavascriptExecutor) driver)
                    .executeScript("return document.readyState").equals("complete")
        );

        logger.info("Search executed and results page loading completed");
    }
}