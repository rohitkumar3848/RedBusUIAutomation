package stepdefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.SearchBusPage;
import pages.SelectBusPage;
import pages.SelectSeatPage;
import utils.ConfigReader;
import utils.NavigationUtil;

public class RedBusSteps {
    SearchBusPage searchPage = new SearchBusPage();;
    SelectBusPage  selectBusPage = new SelectBusPage();;
    SelectSeatPage selectSeatPage= new SelectSeatPage();
    boolean result;

    @When("user launches redBus website url")
    public void user_launches_red_bus_website_url() {
        String title= ConfigReader.get("title");
        NavigationUtil.navigateToHome(title);
    }
    @Given("user is on homepage")
    public void user_is_on_homepage() {
        System.out.println("user is on home page -- PASS");
    }
    @When("user enters from {string} and selects {string}")
    public void user_enters_from_and_selects(String from, String fromSug) {
        searchPage.enterFromDestination(from, fromSug);
    }
    @When("user enters to {string} and selects {string}")
    public void user_enters_to_and_selects(String to, String toSug) {
        searchPage.enterToDestination(to, toSug);
    }
    @When("user selects travel date {string} {string} {string}")
    public void user_selects_travel_date(String day, String month, String year) {
        searchPage.selectDate(day, month, year);
    }
    @When("user searches for buses")
    public void user_searches_for_buses() {
        searchPage.searchBuses();
    }
    @When("user selects bus {string}")
    public void user_selects_bus(String busName) {
        selectBusPage.selectBusByName(busName);
    }
    @Then("user selects first available seat successfully")
    public void user_selects_first_available_seat_successfully() {
        selectSeatPage.displaySoldAndUnsoldSeat();
        result = selectSeatPage.selectFirstAvailableSeat();
        Assert.assertTrue(result, "Seat was not selected successfully!");
    }

}
