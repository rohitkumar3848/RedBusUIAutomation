package utils;

import base.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NavigationUtil {

    public static void navigateToHome(String expectedTitle) {
        try {
            WebDriver driver=DriverFactory.getDriver();
            driver.get(ConfigReader.get("url"));
            driver.manage().window().maximize();
            System.out.println("Check Url-->"+ConfigReader.get("url"));
            WaitUtil.getWait()
                    .until(ExpectedConditions.titleContains(expectedTitle));
        } catch (Exception e) {
            System.out.println("Exception while navigating to home: " + e);
        }
    }
}

