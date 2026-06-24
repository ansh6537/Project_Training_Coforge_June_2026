package hooks;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import base.BaseTest;
import io.cucumber.java.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;

import java.io.ByteArrayInputStream;

public class Hooks extends BaseTest {

    @Before
    public void setUp() throws Exception {

        String browser = getBrowser();

        if (browser.equalsIgnoreCase("chrome")) {

            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--disable-notifications");

            driver = new ChromeDriver(options);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get(getUrl());
    }

    @After
    public void tearDown(Scenario scenario) {

        if (!scenario.isFailed()) {

            try {
                byte[] screenshot =
                        ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

                Allure.addAttachment("ScreenShot ",
                        new ByteArrayInputStream(screenshot));

            } catch (Exception e) {
                System.out.println("Screenshot failed: " + e.getMessage());
            }
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (driver != null) {
            driver.quit();
        }
    }
}