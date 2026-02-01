package pages;

import base.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.ScrollUtil;
import utils.WaitUtil;

import java.util.List;

public class SelectBusPage {

    private final WebDriver driver;

    public SelectBusPage() {
        this.driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//ul[@data-autoid='exact']//li[contains(@class,'tupleWrapper')]")
    private List<WebElement> allBuses;

//    @FindBy(xpath = ".//div[contains(@class,'travelsName')]")
//    private WebElement busNameInsideCard;

    private By viewSeats=By.xpath(".//button[contains(@class,'viewSeatsBtn')]");


    private By busNameInsideCard=By.xpath(".//div[contains(@class,'travelsName')]");

    public void selectBusByName(String expectedBusName) {

        WaitUtil.getWait()
                .until(ExpectedConditions.visibilityOfAllElements(allBuses));

        System.out.println("allBuses and its length--"+allBuses+" "+allBuses.size());

        for (WebElement bus : allBuses) {

            WebElement nameElement =
                    bus.findElement(busNameInsideCard);

            WebElement viewSeatBtn=bus.findElement(viewSeats);
            System.out.println("View SeatBtn check-->"+viewSeatBtn);
            System.out.println("getElement from grid-->"+nameElement.getText());
            String actualName = nameElement.getText().trim();
            System.out.println("actualName--"+actualName+" ---compare--"+expectedBusName);
            if (actualName.toLowerCase().contains(expectedBusName.toLowerCase())) {
                ScrollUtil.scrollToElement(bus);
                WaitUtil.getWait()
                        .until(ExpectedConditions.elementToBeClickable(viewSeatBtn));
                viewSeatBtn.click();
                return;
            }
        }

        throw new RuntimeException("Bus not found: " + expectedBusName);
    }

}
