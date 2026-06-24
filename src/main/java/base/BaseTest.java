package base;

import org.openqa.selenium.WebDriver;
import java.io.InputStream;
import java.util.Properties;

public class BaseTest {

    public static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(WebDriver driver1) {
        driver = driver1;
    }

    // ✅ Load properties properly
    public Properties getProperties() throws Exception {
        Properties prop = new Properties();

        InputStream input = getClass()
                .getClassLoader()
                .getResourceAsStream("data.properties");

        prop.load(input);
        return prop;
    }

    public String getUrl() throws Exception {
        return getProperties().getProperty("url");
    }

    public String getBrowser() throws Exception {
        return getProperties().getProperty("browser");
    }
}