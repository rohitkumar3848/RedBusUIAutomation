package pages;

import base.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.ScrollUtil;
import utils.WaitUtil;

import java.util.List;

public class SelectSeatPage {

    private WebDriver driver;

    public SelectSeatPage() {
        this.driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[@role='button' and contains(@aria-label,'seat status available')]")
    private List<WebElement> availableSeats;

//    @FindBy(xpath =
//            "//*[@role='button' and " +
//                    "contains(@aria-label,'seat status available') and " +
//                    "not(contains(@aria-label,'sold'))]"
//    )

    @FindBy(xpath = ".//div[@role='tab' and contains(@aria-label,'Select seats')]")
    private WebElement seatTabBtn;

    @FindBy(xpath = "//button[contains(@class,'primaryButton')]")
    private WebElement seatBookBtn;

    public void openSelectSeatTab(){
        WaitUtil.getWait().until(ExpectedConditions.elementToBeClickable(seatTabBtn));
        seatTabBtn.click();

    }

    public boolean selectFirstAvailableSeat() {
        openSelectSeatTab();
        WaitUtil.getWait()
                .until(ExpectedConditions.visibilityOfAllElements(availableSeats));
        System.out.println("Total available seats = " + availableSeats.size());
        if (availableSeats.isEmpty()) {
            throw new RuntimeException("No available seats found!");
        }
        WebElement seat = availableSeats.get(0);
        ScrollUtil.scrollToElement(seat);
        WaitUtil.getWait()
                .until(ExpectedConditions.elementToBeClickable(seat));
        seat.click();

        WaitUtil.getWait().until(ExpectedConditions.visibilityOf(seatBookBtn));
        if(seatBookBtn.isDisplayed()){
            return true;
        }
        return false;
    }

}
