package utils;

import base.DriverFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class ScrollUtil {
    public static void scrollToElement(WebElement element){
        WebDriver driver=DriverFactory.getDriver();
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",element);
    }
}