package cucumberOptions;

import org.testng.annotations.DataProvider;
import io.cucumber.testng.*;

@CucumberOptions(
    features = "src/test/resources/Features",
    glue = {"stepDefinations", "hooks"},
    tags = "@zingbus",
    plugin = {
        "pretty",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
    }
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider
    public Object[][] scenarios() {
        return super.scenarios();
    }
}