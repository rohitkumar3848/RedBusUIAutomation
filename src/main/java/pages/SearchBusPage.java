package pages;

import base.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.WaitUtil;

import java.util.List;

public class SearchBusPage {

    private final WebDriver driver;

    @FindBy(id = "srcinput")
    private WebElement fromInput;

    @FindBy(id = "destinput")
    private WebElement destInput;

    @FindBy(xpath = "//div[contains(@class,'searchCategory')]//div[@role='heading']")
    private List<WebElement> fromSuggestions;

    @FindBy(xpath = "//div[contains(@class,'searchCategory')]//div[@role='heading']")
    private List<WebElement> destSuggestions;

    @FindBy(xpath = "//div[contains(@class,'dateInputWrapper')]")
    private WebElement dateBtn;

    @FindBy(xpath = "//p[contains(@class,'monthYear')]")
    private WebElement monthYearHeader;

    @FindBy(xpath = "//i[@aria-label and contains(@aria-label,'Next month')]")
    private WebElement nextBtn;

    @FindBy(xpath = "//div[contains(@class,'calendarDate') and not(contains(@class,'disabled'))]")
    private List<WebElement> availableDates;

    @FindBy(xpath = "//button[@class and contains(@class,'primaryButton')]")
    private WebElement searchBusesBtn;



    public SearchBusPage() {
        this.driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
    }

    public void enterFromDestination(String from, String fromSuggestion) {
        WaitUtil.getWait().until(ExpectedConditions.visibilityOf(fromInput));
        fromInput.clear();
        fromInput.sendKeys(from);
        WaitUtil.getWait().until(ExpectedConditions.visibilityOfAllElements(fromSuggestions));
        WaitUtil.getWait().until(driver ->
                fromSuggestions.size() > 0 &&
                        fromSuggestions.get(0).getText().toLowerCase().contains(from.toLowerCase())
        );
        for (WebElement el : fromSuggestions) {
            if (el.getText().trim().equalsIgnoreCase(fromSuggestion.trim())) {
                WaitUtil.getWait().until(ExpectedConditions.elementToBeClickable(el));
                el.click();
                break;
            }
        }
    }


    public void enterToDestination(String to, String toSuggestion) {
        WaitUtil.getWait().until(ExpectedConditions.visibilityOf(destInput));
        destInput.clear();
        destInput.sendKeys(to);
        WaitUtil.getWait().until(driver ->
                destSuggestions.size() > 0 &&
                        destSuggestions.get(0).getText().toLowerCase().contains(to.toLowerCase())
        );
        for (WebElement el : destSuggestions) {
            System.out.println("el--> is" + el.getText() + "===compare===" + toSuggestion);
            if (el.getText().trim().equalsIgnoreCase(toSuggestion.trim())) {
                WaitUtil.getWait().until(ExpectedConditions.elementToBeClickable(el));
                el.click();
                break;
            }
        }
    }

    public void selectDate(String day,String month,String year){
        //1- open date popUp-
        WaitUtil.getWait().until(ExpectedConditions.elementToBeClickable(dateBtn));
        dateBtn.click();

        //2- comapre month and year
        String target = month + " " + year;
        System.out.println("check target-->"+target);

        while (true) {

            String current = WaitUtil.getWait()
                    .until(ExpectedConditions.visibilityOf(monthYearHeader))
                    .getText().trim();
            System.out.println("check target-->"+target+ " and ==="+current);
            if (current.equalsIgnoreCase(target)) {
                break;
            }

            // click next month
            WaitUtil.getWait().until(ExpectedConditions.elementToBeClickable(nextBtn)).click();
        }

        System.out.println("Got the month year");

        // select day
        for (WebElement d : availableDates) {
            System.out.println("Available date--"+d.getText().trim()+"=="+day);
            if (d.getText().trim().equals(day)) {
                WaitUtil.getWait().until(ExpectedConditions.elementToBeClickable(d));
                d.click();
                break;
            }
        }
    }

    public void searchBuses(){
        WaitUtil.getWait().until(ExpectedConditions.elementToBeClickable(searchBusesBtn));
        searchBusesBtn.click();
    }
}
