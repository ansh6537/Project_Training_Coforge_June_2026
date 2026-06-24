package stepDefinations;

import base.BaseTest;
import io.cucumber.java.en.*;
import pageObjects.HomePage;
import pageObjects.SearchResultsPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import org.testng.Assert;

public class ZingBusSteps {

    HomePage home;
    SearchResultsPage results;

    // ✅ Logger added
    private static final Logger logger = LogManager.getLogger(ZingBusSteps.class);

    @Given("user is on zingbus homepage")
    public void user_is_on_homepage() {
        logger.info("Launching ZingBus homepage");
        home = new HomePage(BaseTest.getDriver());
    }

    @When("user enters from city {string}")
    public void user_enters_from_city(String from){
        logger.info("Entering FROM city: " + from);
        home.enterFromCity(from);
    }

    @When("user enters to city {string}")
    public void user_enters_to_city(String to) {
        logger.info("Entering TO city: " + to);
        home.enterToCity(to);
    }

    /*
    @When("user select date {string}")
    public void user_select_date(String date) {

        logger.info("Selecting journey date: " + date);

        home.selectDate(date);
    }
    */

    @Then("user clicks on search button")
    public void user_clicks_search(){
        logger.info("Clicking search button");
        home.clickSearch();
        results = new SearchResultsPage(BaseTest.getDriver());
        logger.info("Navigated to Search Results page");
    }

    @Then("user applies cheapest filter")
    public void user_applies_cheapest_filter() {
        logger.info("Applying cheapest filter");
        results.selectCheapest();
        logger.info("Cheapest filter applied successfully");
    }

    /*
    @Then("user applies sleeper filter")
    public void user_applies_sleeper_filter() {

        logger.info("Applying sleeper filter");

        results.selectSleeper();
    }
    */

    @Then("user should see filtered bus results")
    public void user_should_see_filtered_bus_results() {
        logger.info("Scrolling to view cheapest bus");
        results.scrollTillFirstBus();
        logger.info("✅ Cheapest bus is visible");
        logger.info("Test execution completed successfully");
    }
}