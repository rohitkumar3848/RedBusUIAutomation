package utils;

import base.DriverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class ScreenshotUtil {

    public static void screenShot(String name){
        try{
            WebDriver driver= DriverFactory.getDriver();
            TakesScreenshot shot=((TakesScreenshot) driver);
            File source =shot.getScreenshotAs(OutputType.FILE);
            String path=System.getProperty("user.dir")+"//target//Screenshot//"+name+".png";
            File destination=new File(path);
            FileUtils.copyFile(source,destination);
        }
        catch (Exception e){
            System.out.println("Exception in screenshot--");
        }
    }
}
