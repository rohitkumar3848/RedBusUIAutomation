package pages;

import base.DriverFactory;
import org.openqa.selenium.By;
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


    @FindBy(xpath = ".//div[@role='tab' and contains(@aria-label,'Select seats')]")
    private WebElement seatTabBtn;

    @FindBy(xpath = "//button[contains(@class,'primaryButton')]")
    private WebElement seatBookBtn;

    @FindBy(xpath = "//span[@role='button' and contains(@aria-label,'seat status')]")
    private List<WebElement> allSeats;



    public void openSelectSeatTab(){
        WaitUtil.getWait().until(ExpectedConditions.elementToBeClickable(seatTabBtn));
        seatTabBtn.click();
    }

    public boolean selectFirstAvailableSeat() {
        openSelectSeatTab();
        WaitUtil.getWait()
                .until(ExpectedConditions.visibilityOfAllElements(availableSeats));
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

    public void displaySoldAndUnsoldSeat(){
        WaitUtil.getWait()
                .until(ExpectedConditions.visibilityOfAllElements(allSeats));
        if (allSeats.isEmpty()) {
            throw new RuntimeException("No seats found!");
        }
        for(WebElement seat:allSeats){
            WaitUtil.getWait().until(ExpectedConditions.visibilityOf(seat));
            String seatAttr=seat.getAttribute("aria-label");
           // System.out.println("Seat attr -->"+seatAttr);
            seatAttr = seatAttr.replace(",", "").trim();
            String seatNumber = "";
            String seatStatus = "";
            String[] words = seatAttr.split("\\s+");
            for (int i = 0; i < words.length; i++) {
                if (words[i].equalsIgnoreCase("number")) {
                    seatNumber = words[i + 1];
                }
                if (words[i].equalsIgnoreCase("reserved")) {
                    seatStatus = "SOLD";
                }
                if (words[i].equalsIgnoreCase("unreserved")) {
                    seatStatus = "UNSOLD";
                }
            }
            System.out.println("Seat Number: "+seatNumber+" Status: "+seatStatus);
        }
    }

}
