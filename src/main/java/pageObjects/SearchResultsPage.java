package pageObjects;

import org.openqa.selenium.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class SearchResultsPage {

    WebDriver driver;
    WebDriverWait wait;

    // ✅ FIX: added semicolon
    private static final Logger logger = LogManager.getLogger(SearchResultsPage.class);

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    private By sortDropdown = By.xpath("//*[@id=\"filterContainer\"]/div[3]/div[2]/div[1]");
    private By cheapestOption = By.xpath("//div[contains(text(),'Cheapest')]");

    // ✅ APPLY CHEAPEST FILTER
    public void selectCheapest() {

        logger.info("Clicking on sort dropdown");

        wait.until(ExpectedConditions.elementToBeClickable(sortDropdown)).click();

        try { Thread.sleep(1000); } catch (Exception e) {}

        logger.info("Selecting cheapest filter option");

        wait.until(ExpectedConditions.elementToBeClickable(cheapestOption)).click();

        logger.info("Waiting for filter results to load");

        try { Thread.sleep(3000); } catch (Exception e) {}

        logger.info("Cheapest filter applied successfully");
    }

    // ✅ SCROLL UNTIL FIRST BUS VISIBLE
    public void scrollTillFirstBus() {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            logger.info("Starting scroll to bring cheapest bus into view");

            js.executeScript("window.scrollBy(0, 300)");

            Thread.sleep(1500);

            js.executeScript("window.scrollTo(0, 200)");

            Thread.sleep(1500);

            logger.info("Cheapest bus is now visible at top");

        } catch (InterruptedException ex) {

            logger.error("Exception occurred during scrolling", ex);
        }
    }
}